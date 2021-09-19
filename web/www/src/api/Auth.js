export default {
    auth,
    register,
    refresh,
};

function _fetch(url, data) {
    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => {
        if (!res.ok) {
            return Promise.reject(res.statusText)
        }
        return res;
    }).then(res => res.json());
}

function auth(username, password) {
    let data = {
        client_id: "login",
        grant_type: "password",
        username: username,
        password: password
    };

    let formBody = [];
    for (let property in data) {
        let encodedKey = encodeURIComponent(property);
        let encodedValue = encodeURIComponent(data[property]);
        formBody.push(encodedKey + "=" + encodedValue);
    }

    return fetch('/auth/token', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: formBody.join("&")
    }).then(res => {
        if (!res.ok) {
            return Promise.reject(res.json())
        }
        return res;
    }).then(res => res.json());
}

function register(username, password) {
    let data = {
        client_id: "register",
        grant_type: "",
        username: username,
        password: password
    };
    return _fetch('/register', data);
}

function refresh(refreshToken) {
    let data = {
        client_id: "login",
        grant_type: "refresh_token",
        refresh_token: refreshToken
    };
    return _fetch('/auth/refresh', data);
}



