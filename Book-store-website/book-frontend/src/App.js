import { Route, Routes } from "react-router-dom";
import "./App.css";
import LayoutAdmin from "./components/LayoutAdmin";
import LayoutAnonymous from "./components/LayoutAnonymous";
import NotFound from "./components/NotFound";
import PrivateRoutes from "./components/PrivateRoutes";
import PrivateUserRoutes from "./components/PrivateUserRoutes";
import AuthorList from "./pages/admin/author/AuthorList";
import BookAdminCreate from "./pages/admin/book/BookAdminCreate";
import BookAdminDetail from "./pages/admin/book/BookAdminDetail";
import BookAdminList from "./pages/admin/book/BookAdminList";
import CategoryList from "./pages/admin/category/CategoryList";
import Login from "./pages/admin/Login";
import OrderDetail from "./pages/admin/order/OrderDetail";
import OrderList from "./pages/admin/order/OrderList";
import UserAdminCreate from "./pages/admin/user/UserAdminCreate";
import UserAdminDetail from "./pages/admin/user/UserAdminDetail";
import UserAdminList from "./pages/admin/user/UserAdminList";
import BookByAuthor from "./pages/anonymous/BookByAuthor";
import BookDetail from "./pages/anonymous/BookDetail";
import BookList from "./pages/anonymous/BookList";
import Contact from "./pages/anonymous/Contact";
import Register from "./pages/anonymous/Register";
import HistoryOrder from "./pages/user/HistoryOrder";
import ShoppingCart from "./pages/user/ShoppingCart";

function App() {
  return (
    <div>
      <Routes>
        {/* Anonymous */}
        <Route path="/" element={<LayoutAnonymous />}>
          <Route index element={<BookList />} />
          <Route path="books/:bookId/:slug" element={<BookDetail />} />
          <Route path="books/author/:authorId/:authorName" element={<BookByAuthor/>}/>
          <Route path="contact" element={<Contact/>}/>
          {/* User  */}
          <Route path="user">
            <Route element={<PrivateUserRoutes/>}>
            <Route path="shopping-cart" element={<ShoppingCart />}></Route>
            <Route path="history-order" element={<HistoryOrder/>}/>
            </Route>
          </Route>
        </Route>
       
        {/* Admin */}
        <Route path="/admin" element={<LayoutAdmin />}>
          <Route element={<PrivateRoutes />}>
            <Route path="books">
              <Route index element={<BookAdminList />} />
              <Route path=":bookId" element={<BookAdminDetail />} />
              <Route path="create" element={<BookAdminCreate />} />
            </Route>
            <Route path="categories" element={<CategoryList />} />
            <Route path="authors" element={<AuthorList />} />

            {/* User */}
            <Route path="users">
              <Route index element={<UserAdminList />} />
              <Route path=":userId" element={<UserAdminDetail />} />
              <Route path="create" element={<UserAdminCreate />} />
            </Route>
            <Route path="orders">
              <Route index element={<OrderList/>}/>
              <Route path=":orderId" element={<OrderDetail/>}/>
            </Route>
          </Route>
        </Route>
        <Route path="/login" element={<Login />} />
        <Route path="/page-register" element={<Register/>}/>
        <Route path="/not-found" element={<NotFound />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </div>
  );
}

export default App;
