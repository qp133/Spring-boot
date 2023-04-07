import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { logout } from "../../app/slices/authSlice";

function HeaderAdmin() {
  const { auth } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const handleLogout = () => {
    dispatch(logout());
  };

  return (
    <div >
      <header
        id="header"
        className="header fixed-top d-flex align-items-center"
      >
        <div className="d-flex align-items-center justify-content-between">
          <p className="logo d-flex align-items-center">
            <img src="assets/img/logo.png" alt="" />
            <span className="d-none d-lg-block">Stupid Admin</span>
          </p>
         
        </div>
        {/*-- End Logo */}

        <nav className="header-nav ms-auto">
          <ul className="d-flex align-items-center">
        

            <li className="nav-item dropdown pe-3">
              <p
                className="nav-link nav-profile d-flex align-items-center pe-0"
                data-bs-toggle="dropdown"
              >
                <img
                  src={auth?.avatar?`${auth?.avatar}`: "https://via.placeholder.com/150"}
                  className="rounded-circle"
                  style={{ width: "40px" }}
                  alt={auth?.name}
                />
                <span className="d-none d-md-block dropdown-toggle ps-2">
                  {auth?.name}
                </span>
              </p>
              {/*-- End Profile Iamge Icon */}

              <ul className="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                <li className="dropdown-header">
                  <h6>{auth?.name}</h6>
                  <span>stupid admin</span>
                </li>
                <li>
                  <hr className="dropdown-divider" />
                </li>

                <li>
                  <hr className="dropdown-divider" />
                </li>

                <li>
                  <button
                    className="dropdown-item d-flex justify-content-center align-items-center"
                    onClick={handleLogout}
                  >
                    <i className="bi bi-box-arrow-right"></i>
                    <span>Đăng xuất</span>
                  </button>
                </li>
              </ul>
              {/*-- End Profile Dropdown Items */}
            </li>
            {/*-- End Profile Nav */}
          </ul>
        </nav>
        {/*-- End Icons Navigation */}
      </header>
   
     
    </div>
  );
}

export default HeaderAdmin;
