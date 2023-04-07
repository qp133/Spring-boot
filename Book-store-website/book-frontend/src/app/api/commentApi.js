import axiosClient from "./axiosClient";

const commentApi = {
    getComments(id) {
        const url = `/comments/${id}`;
        return axiosClient.get(url);
    },
}

export default commentApi;