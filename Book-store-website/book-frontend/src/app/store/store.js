import { configureStore } from "@reduxjs/toolkit";
import { authService } from "../services/authSevice";
import baseApi from "../services/baseService";
import authReducer from "../slices/authSlice";
import { categoryService } from "../services/categoryService";
import categoryReducer from "../slices/categorySlice";
import authorReducer from "../slices/authorSlice";
import { authorService } from "../services/authorSevice";
import { userService } from "../services/userSevice";
import userReducer from "../slices/userSlice";
import { bookService } from "../services/bookService";
import bookReducer from "../slices/bookSlice";
import { orderItemService } from "../services/orderItemService";
import orderItemReducer from "../slices/orderItemSlice";
import { orderService } from "../services/orderService";
import orderReducer from "../slices/orderSlice";
import { orderUserService } from "../services/orderUserService";
import historyOrderReducer from "../slices/historyOrderSlice";
import newBookReducer from "../slices/newBookSlice";
import deleteBookReducer from "../slices/deleteBookSlice";
import updateBookReducer from "../slices/updateBookSlice";

const store = configureStore({
    reducer: {
        [authService.reducerPath]: authService.reducer,
        [baseApi.reducerPath]: baseApi.reducer,
        [categoryService.reducerPath]: categoryService.reducer,
        [authorService.reducerPath]:authorService.reducer,
        [userService.reducerPath]:userService.reducer,
        [bookService.reducerPath]:bookService.reducer,
        [orderItemService.reducerPath]:orderItemService.reducer,
        [orderService.reducerPath]:orderService.reducer,
        [orderUserService.reducer]:orderUserService.reducer,
        auth: authReducer,
        categories: categoryReducer,
        authors:authorReducer,
        users:userReducer,
        books:bookReducer,
        newBooks:newBookReducer,
        deleteBooks:deleteBookReducer,
        updateBooks:updateBookReducer,
        orderItems:orderItemReducer,
        orders:orderReducer,
        historyOrders:historyOrderReducer,
    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(
            authService.middleware,
            baseApi.middleware,
            categoryService.middleware,
            authorService.middleware,
            userService.middleware,
            bookService.middleware,
            orderItemService.middleware,
            orderService.middleware,
            orderUserService.middleware,
        ),
});

export default store;