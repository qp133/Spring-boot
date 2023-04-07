import React from 'react'
import { Link } from 'react-router-dom'

function Sidebar() {
  return (
    <div>

         <div id="sidebar" className="sidebar">
         <ul className="sidebar-nav" id="sidebar-nav">
           {/* books  */}
           <li className="nav-item">
             <Link to={"/"} className="nav-link ">
             <i className="fa-solid fa-house"></i>
               <span>Trang chủ</span>
             </Link>
           </li>
           <li className="nav-heading">Quản lý</li>
           <li className="nav-item">
             <p
              
               className="nav-link collapsed item-sidebar"
               data-bs-target="#books-nav"
               data-bs-toggle="collapse"
             >
               <span><i className="fa-solid fa-book-open"></i> Books</span>
               <i className="fa-solid fa-chevron-down"></i>
             </p>
             <ul
               id="books-nav"
               className="nav-content collapse "
               data-bs-parent="#sidebar-nav"
             >
               <li>
                 <Link to={"/admin/books"}>
                   <i className="bi bi-circle"></i>
                   <span>Tất cả sách</span>
                 </Link>
               </li>
               <li>
                 <Link to={"/admin/books/create"}>
                   <i className="bi bi-circle"></i>
                   <span>Thêm sách mới</span>
                 </Link>
               </li>
             </ul>
           </li>
           {/*end books  */}
           {/* categories  */}
           <li className="nav-item">
             <p
               className="nav-link collapsed item-sidebar"
               data-bs-target="#categories-nav"
               data-bs-toggle="collapse"
             >
               
               <span><i className="fa-solid fa-folder"></i> Categories</span>
               <i className="fa-solid fa-chevron-down"></i>
             </p>
             <ul
               id="categories-nav"
               className="nav-content collapse "
               data-bs-parent="#sidebar-nav"
             >
               <li>
                 <Link to={"/admin/categories"}>
                   <i className="bi bi-circle"></i>
                   <span>Tất cả thể loại</span>
                 </Link>
               </li>
             </ul>
           </li>
           {/*end categories  */}
           {/* authors  */}
           <li className="nav-item">
             <p
               className="nav-link collapsed item-sidebar"
               data-bs-target="#authors-nav"
               data-bs-toggle="collapse"
             >
               <span><i className="fa-solid fa-user-tie"></i> Authors</span>
               <i className="fa-solid fa-chevron-down"></i>
             </p>
             <ul
               id="authors-nav"
               className="nav-content collapse "
               data-bs-parent="#sidebar-nav"
             >
               <li>
                 <Link to={"/admin/authors"}>
                   <i className="bi bi-circle"></i>
                   <span>Tất cả tác giả</span>
                 </Link>
               </li>
             </ul>
           </li>
           {/*end authors  */}
           {/* users  */}
           <li className="nav-item">
             <p
               className="nav-link collapsed item-sidebar"
               data-bs-target="#users-nav"
               data-bs-toggle="collapse"
             >
               <span><i className="fa-regular fa-user"></i> Users</span>
               <i className="fa-solid fa-chevron-down"></i>
             </p>
             <ul
               id="users-nav"
               className="nav-content collapse "
               data-bs-parent="#sidebar-nav"
             >
               <li>
                 <Link to={"/admin/users"}>
                   <i className="bi bi-circle"></i>
                   <span>Tất cả users</span>
                 </Link>
               </li>
               <li>
                 <Link to={"/admin/users/create"}>
                   <i className="bi bi-circle"></i>
                   <span>Thêm user mới </span>
                 </Link>
               </li>
             </ul>
           </li>
           {/*-- End Components Nav */}
 
           <li className="nav-heading">Pages</li>
 
           <li className="nav-item">
             <p
               className="nav-link collapsed item-sidebar"
               data-bs-target="#orders-nav"
               data-bs-toggle="collapse"
             >
               <span><i className="fa-regular fa-user"></i> Orders</span>
               <i className="fa-solid fa-chevron-down"></i>
             </p>
             <ul
               id="orders-nav"
               className="nav-content collapse "
               data-bs-parent="#sidebar-nav"
             >
               <li>
                 <Link to={"/admin/orders"}>
                   <i className="bi bi-circle"></i>
                   <span>Tất cả đơn hàng </span>
                 </Link>
               </li>
             </ul>
           </li>
           {/*-- End Profile Page Nav */}
         </ul>
       </div>
    </div>
  )
}

export default Sidebar