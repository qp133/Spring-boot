import React from "react";
import { useSelector } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";
import { toast } from "react-toastify";
function PrivateUserRoutes() {
     // auth = state.auth.auth
    // isAuthenticated = state.auth.isAuthenticated
    const {  isAuthenticated } = useSelector((state) => state.auth);

    // Nếu chưa đăng nhập => quay về trang login
    if (!isAuthenticated) {
        toast.error("Bạn cần đăng nhập để tiếp tục")
        return <Navigate to={"/login"} />;
    }

    // // Nếu đã đăng nhập, mà không có quyền 
    // if(!auth.roles.includes("ADMIN")) {
    //     return <Navigate to={"/"} />;
    // }


    return <Outlet />;
}

export default PrivateUserRoutes