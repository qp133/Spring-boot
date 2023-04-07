import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from "react-redux";
import { logout } from "../../app/slices/authSlice";

function Header() {
  const navigate=useNavigate();
  const { auth } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  
  const handleLogout = () => {
    dispatch(logout());
    navigate("login")
  };
  return (
    <header className="tm-site-header">
    <h1 className="tm-site-name">Stupid</h1>
    
    <p className="tm-site-description">Your Online Bookstore</p>
    
    <nav className="navbar navbar-expand-md tm-main-nav-container">
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#tmMainNav" aria-controls="tmMainNav" aria-expanded="false" aria-label="Toggle navigation">
                <i className="fa fa-bars"></i>
        </button>

        <div className="collapse navbar-collapse tm-main-nav menu-header" id="tmMainNav">
            <ul className="nav nav-fill tm-main-nav-ul ">
                <li className="nav-item"><Link className="nav-link"to={"/"}>Home</Link></li>
                
                <li className="nav-item"><Link to={"user/history-order"} className="nav-link" >Lịch sử mua hàng</Link></li>
                <li className="nav-item"><Link to={"/contact"} className="nav-link" >Liên hệ</Link></li>
                <li className="nav-item"><Link to={"/user/shopping-cart"}  className="nav-link" >Giỏ hàng</Link></li>
                <li className="nav-item dropdown ">
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
                  {auth?.name??"Chưa đăng nhập"}
                </span>
              </p>
              {/*-- End Profile Iamge Icon */}

              <ul className="dropdown-menu dropdown-menu-arrow profile login-dropdown">
                <li className="dropdown-header">
                  <h6>{auth?.name}</h6>
                  <span>Khách hàng</span>
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
                    <p style={{'fontSize':'15px'}}>Đăng nhập/Đăng xuất</p>
                  </button>
                </li>
              </ul>
              {/*-- End Profile Dropdown Items */}
            </li>
               
            </ul>
        </div>
    </nav>
    
</header>
  )
}

export default Header