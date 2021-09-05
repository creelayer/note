// import Auth from "@/api/Auth"
// import Router from "@/router/index"

const METHOD_GET = 'GET';
const METHOD_POST = 'POST';
const METHOD_DELETE = 'DELETE';

export default {
    token: {
        access_token: null,
        refresh_token: null
    },
    getRefreshToken: function () {
        return this.token ? this.token.refresh_token : null;
    },
    getAccessToken: function () {
        return this.token ? this.token.access_token : null;
    },
    get,
    post,
    del,
    request

    // put,

};

function get(url) {
    return this.request(METHOD_GET, url)
}

function post(url, body) {
    return this.request(METHOD_POST, url, body)
}

function del(url) {
    return this.request(METHOD_DELETE, url)
}

function request(method, url, body) {

    // if (this.getAccessToken() == null) {
    //     Router.replace("/login");
    //     return Promise.reject("Token expired");
    // }

    // let that = this;
    const requestOptions = {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.getAccessToken(),
        },
        body: JSON.stringify(body)
    };

    function onResponse(response) {

        if (response.ok) {
            return response;
        }

        // if (response.status === 401) {
        //     Auth.refresh(that.getRefreshToken()).then(token => {
        //         that.token = token;
        //         return this.request(method, url);
        //     }).catch(() => {
        //         that.token = null;
        //         Router.replace("/login");
        //         return Promise.reject("Token expired");
        //     });
        // }

        throw Error(response.statusText);
    }

    return fetch(url, requestOptions).then(onResponse).then(res => res.json());
}

//
// function put(url, body) {
//     const requestOptions = {
//         method: 'PUT',
//         headers: {'Content-Type': 'application/json'},
//         body: JSON.stringify(body)
//     };
//     return fetch(url, requestOptions).then(handleResponse);
// }
//
// function _delete(url) {
//     const requestOptions = {
//         method: 'DELETE'
//     };
//     return fetch(url, requestOptions).then(handleResponse);
// }
//
// function handleResponse(response) {
//
//
//     if(response.ok){
//         return response;
//     }
//
//     if(response.status === 401){
//         if(Auth.refresh()){
//
//         }
//     }
//
//     console.log(response);
//
//     return response;
//
//     // return response.text().then(text => {
//     //     const data = text && JSON.parse(text);
//     //
//     //     if (!response.ok) {
//     //         const error = (data && data.message) || response.statusText;
//     //         return Promise.reject(error);
//     //     }
//     //
//     //     return data;
//     // });
// }

// function handleError(response) {
//
//     if (response.ok) {
//         return response;
//     }
//
//     if (response.status === 403) {
//         alert("403");
//     }
// }