package softuni.exam.models.dto.jobImports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobImportRoot {

    @XmlElement(name = "job")
    private List<JobImportDTO> jobs;

    public JobImportRoot() {
    }

    public List<JobImportDTO> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobImportDTO> jobs) {
        this.jobs = jobs;
    }
}
