function lockedProfile() {
    let showMoreButtons = Array.from(document.querySelectorAll("#main .profile > button"));
    for (const btn of showMoreButtons) {
        btn.addEventListener("click", showOrHideMoreInfo);
    }

    function showOrHideMoreInfo(e) {
        let btn = e.target;
        let profile = e.target.parentElement;

        let profileChildren = profile.children;
        let moreInfo = profileChildren[9];
        let lock = profileChildren[2];

        if (lock.checked) {
            return;
        }

        if (btn.textContent === "Show more") {
            btn.textContent = "Hide it";
            moreInfo.style.display = "block";
        } else {
            btn.textContent = "Show more";
            moreInfo.style.display = "none";
        }
    }

}