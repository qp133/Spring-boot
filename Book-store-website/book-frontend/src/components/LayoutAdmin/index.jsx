import React from "react";
import { Outlet } from "react-router-dom";
import HeaderAdmin from "../HeaderAdmin";
import Sidebar from "../Sidebar";

function LayoutAdmin() {
  return (
    <div>
      {/*-- ======= Header ======= */}
      <HeaderAdmin />

      {/*-- ======= Sidebar ======= */}
      <Sidebar />
      {/*-- End Sidebar*/} 
      <div className="container-layoutAdmin">
            <Outlet/>
        </div>
     
    </div>
  );
}

export default LayoutAdmin;
