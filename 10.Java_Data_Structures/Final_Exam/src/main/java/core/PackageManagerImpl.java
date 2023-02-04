package core;

import models.Package;

import java.time.LocalDateTime;
import java.util.*;

public class PackageManagerImpl implements PackageManager {

    static class NameAndVersion {
        private final String name;
        private final String version;

        public NameAndVersion(String name, String version) {
            this.name = name;
            this.version = version;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NameAndVersion that = (NameAndVersion) o;

            if (!name.equals(that.name)) return false;
            return version.equals(that.version);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + version.hashCode();
            return result;
        }
    }
    
    static class NameAndDateAndVersion {
        private String name;
        private LocalDateTime releaseDate;
        private String version;

        public NameAndDateAndVersion(String name, LocalDateTime releaseDate, String version) {
            this.name = name;
            this.releaseDate = releaseDate;
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public LocalDateTime getReleaseDate() {
            return releaseDate;
        }

        public String getVersion() {
            return version;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NameAndDateAndVersion that = (NameAndDateAndVersion) o;

            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    private final Map<String, Package> packagesById = new HashMap<>();
    private final Map<NameAndVersion, Package> packagesByNameAndVersion = new HashMap<>();
    private final Map<String, Set<Package>> dependentOn = new HashMap<>();
    private final Map<String, Set<Package>> dependencies = new HashMap<>();

    private final Set<Package> independentPackages = new TreeSet<>(
            releaseDateDescVersionAsc()
    );

    private final Map<NameAndDateAndVersion, Package> packagesByName = new TreeMap<>(
            Comparator.comparing(NameAndDateAndVersion::getReleaseDate).reversed()
                    .thenComparing(NameAndDateAndVersion::getVersion)
    );

    private static Comparator<Package> releaseDateDescVersionAsc() {
        return Comparator.comparing(Package::getReleaseDate).reversed().thenComparing(Package::getVersion);
    }

    @Override
    public void registerPackage(Package _package) {
        NameAndVersion nameAndVersion = new NameAndVersion(_package.getName(), _package.getVersion());
        if (packagesByNameAndVersion.containsKey(nameAndVersion)) {
            throw new IllegalArgumentException();
        }

        packagesById.put(_package.getId(), _package);

        addToIndices(_package, nameAndVersion);
    }

    private void addToIndices(Package _package, NameAndVersion nameAndVersion) {
        packagesByNameAndVersion.put(nameAndVersion, _package);
        independentPackages.add(_package);

        NameAndDateAndVersion key = new NameAndDateAndVersion(
                _package.getName(), _package.getReleaseDate(), _package.getVersion());

        Package packageByName = packagesByName.get(key);

        if (packageByName != null) {
            int compare = releaseDateDescVersionAsc().compare(_package, packageByName);
            if (compare < 0) {
                packagesByName.put(key, _package);
            }
        }
    }

    @Override
    public void removePackage(String packageId) {
        Package _package = packagesById.get(packageId);
        if (_package == null) {
            throw new IllegalArgumentException();
        }

        packagesById.remove(packageId);
        removeFromIndices(_package);
    }

    private void removeFromIndices(Package _package) {
        packagesByNameAndVersion.remove(new NameAndVersion(_package.getName(), _package.getVersion()));
        for (Package aPackage : dependencies.get(_package.getId())) {
            dependentOn.computeIfAbsent(aPackage.getId(), k -> new HashSet<>()).remove(_package);
        }
        dependencies.remove(_package.getId());
        independentPackages.remove(_package);
        packagesByName.remove(new NameAndDateAndVersion(
                _package.getName(), _package.getReleaseDate(), _package.getVersion()));
    }

    @Override
    public void addDependency(String packageId, String dependencyId) {
        Package independent = packagesById.get(dependencyId);  // B
        Package dependent = packagesById.get(packageId); // A

        if (independent == null || dependent == null) {
            throw new IllegalArgumentException();
        }

        addToDependencyIndices(packageId, dependencyId, independent, dependent);
    }

    private void addToDependencyIndices(String packageId, String dependencyId, Package independent, Package dependent) {
        dependencies.computeIfAbsent(packageId, k -> new HashSet<>()).add(independent);
        dependentOn.computeIfAbsent(dependencyId, k -> new HashSet<>()).add(dependent);
        independentPackages.remove(dependent);
    }

    @Override
    public boolean contains(Package _package) {
        return packagesById.containsKey(_package.getId());
    }

    @Override
    public int size() {
        return packagesById.size();
    }

    @Override
    public Iterable<Package> getDependants(Package _package) {
        Set<Package> packages = dependentOn.get(_package.getId());
        return packages != null ? packages : Collections.emptySet();
    }

    @Override
    public Iterable<Package> getIndependentPackages() {
        return independentPackages.size() != 0 ? independentPackages : Collections.emptySet();
    }

    @Override
    public Iterable<Package> getOrderedPackagesByReleaseDateThenByVersion() {
        return packagesByName.values();
    }

}
