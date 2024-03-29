import { configureStore } from "@reduxjs/toolkit";
import { authService } from "../services/authService";
import baseApi from "../services/baseService";
import authReducer from "../slices/authSlice";
import blogReducer from "../slices/blogSlice";
import categoryReducer from "../slices/categorySlice";

const store = configureStore({
    reducer: {
        [authService.reducerPath]: authService.reducer,
        [baseApi.reducerPath]: baseApi.reducer,
        auth: authReducer,
        blogs: blogReducer,
        categories: categoryReducer,
    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(
            authService.middleware,
            baseApi.middleware,
        ),
});

export default store;
