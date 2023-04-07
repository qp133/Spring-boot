import axiosClient from "./axiosClient";

const bookApi = {
    getBooks(query) {
        const url = `/books?${query}`;
        return axiosClient.get(url);
    },
    getBookById(id, slug) {
        const url = `/books/${id}/${slug}`;
        return axiosClient.get(url);
    },
    getBookByAuthorid(id){
        const url=`/books/author/${id}`;
        return axiosClient.get(url)
    }
}

export default bookApi;