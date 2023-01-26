export const isLoggedIn = () => {
    let data = localStorage.getItem("data")

    if(data == null)
        return false;
    else
        return true;
}

export const doLogin = ({data}: { data: any }) => {
    localStorage.setItem("data", JSON.stringify(data))
}

export const getUserRole = () => {
    if(isLoggedIn()) {
        // @ts-ignore
        console.log(JSON.parse(localStorage.getItem("data")).user.role)
        // @ts-ignore
        return JSON.parse(localStorage.getItem("data")).user.role
    } else return false;
}

export const doLogout = () => {
    localStorage.removeItem("data")
    // route()
}

export const getCurrentUser = () => {
    if(isLoggedIn()) {
        // @ts-ignore
        return JSON.parse(localStorage.getItem("data")).user
    } else
        return false
}