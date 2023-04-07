import axiosClient from "./axiosClient";

const registerApi = {
    createAccount(newUser) {
        const url = "/handle-create-account";
        return axiosClient.post(url, newUser);
    },
}

export default registerApi;