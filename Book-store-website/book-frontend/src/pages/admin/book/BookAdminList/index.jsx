import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import {
  useGetBooksQuery,
  useDeleteBookMutation,
} from "../../../../app/services/bookService";
import {
  Pagination,
  PaginationItem,
  PaginationLink,
  Container,
} from "reactstrap";
import { useState } from "react";
import store from "../../../../app/store/store";
function BookAdminList() {
  const state = store.getState;
  const { newBooks } = useSelector((state) => state.newBooks);
  const { deleteBooks } = useSelector((state) => state.deleteBooks);
  const { updateBooks } = useSelector((state) => state.updateBooks);
  const{ books}= useSelector((state) => state.books);
  const [query, setQuery] = useState("books");
  const { isLoading } = useGetBooksQuery(query);
  const [page, setPage] = useState(1);
  const [cloneBooks, setCloneBooks] = useState();
  const { bookss, pageNumber, totalPages } = useGetBooksQuery(query, {
    selectFromResult: ({ data }) => ({
      bookss: data?.books,
      pageNumber: data?.pageNumber,
      totalPages: data?.totalPages,
    }),
  });

  const [booksDisplay, setBooksDisplay] = useState();

  const [deleteBook] = useDeleteBookMutation();

  useEffect(() => {
    const fetchBooks = async () => {
      if (bookss) {
        try {
          const booksCopy = bookss.slice();
          //Xóa sách
          for (let i = booksCopy.length - 1; i >= 0; i--) {
            if (deleteBooks.indexOf(booksCopy[i].id) !== -1) {
              booksCopy.splice(i, 1);
            }
          }
          //Sách đã cập nhật
          for (let i = 0; i < booksCopy.length; i++) {
            let index = updateBooks.findIndex(book => book.id === booksCopy[i].id);
            if (index !== -1) {
              booksCopy.splice(i, 1, updateBooks[index]);
            }
          }
          if(page===1){
            //Thêm sách mới
            if(newBooks?.length>0){
              for (let i = 0; i < newBooks.length; i++) {
                booksCopy.unshift(newBooks[i]);
              }
              setBooksDisplay(booksCopy)
            }
            else{
              setBooksDisplay(booksCopy)
            }
          }
        else{
          setBooksDisplay(booksCopy);
        }
        } catch (error) {
          console.log(error);
        }
      }
    };

    fetchBooks();
  }, [bookss]);

  let pageDisplay = [];
  for (let i = 1; i <= totalPages; i++) {
    pageDisplay.push(i);
  }
  const handleDeleteBook = (id) => {
    const isConfirm = window.confirm("Bạn có muốn xóa không?");
    if (isConfirm) {
      deleteBook(id)
        .unwrap()
        .then(() => {
          alert("Xóa thành công");
          const booksCopy = booksDisplay.slice();
          let index = booksCopy.findIndex((book) => book.id === id);
          booksCopy.splice(index, 1);
          setBooksDisplay(booksCopy);
          setCloneBooks(booksCopy);
        })
        .catch((err) => console.log(err));
    }
  };

  useEffect(() => {
    const handleChangePage = () => {
      setQuery(`books?page=${page}`);
    };
    handleChangePage();
  }, [page]);

  console.log(books);
 
  // console.log(updateBooks)
  
  return (
    <div className="course-list mt-4 mb-4">
      <div className="container">
        <div className="mb-4">
          <Link
            to={"/admin/books/create"}
            className="btn-custom btn-create-course"
          >
            <button className="btn btn-success">
              <span>
                <i className="fa-solid fa-plus"></i>
              </span>
              Thêm sách mới
            </button>
          </Link>
        </div>

        <div className="course-list-inner p-2">
          <table className="table course-table book-table">
            <thead>
              <tr>
                <th>STT</th>
                <th>Thumbnail</th>
                <th>Tên</th>
                <th>Thể loại</th>
                <th>Tác giả</th>
                <th>Số trang</th>
                <th>Giá tiền</th>
                <th>Năm xuất bản</th>
                <th>Số lượng</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {booksDisplay?.map((book, index) => (
                <tr key={book.id}>
                  <td>{index + 1}</td>
                  <td>
                    {" "}
                    <img
                      src={
                        book?.thumbnail
                          ? `${book?.thumbnail}`
                          : "https://via.placeholder.com/150"
                      }
                      alt="thumbnail"
                      id="thumbnail-preview"
                      style={{
                        width: "60px",
                        height: "90px",
                        objectFit: "cover",
                      }}
                    />
                  </td>
                  <td>
                    <Link to={`/admin/books/${book.id}`}>{book.title}</Link>
                  </td>
                  <td>{book?.categories?.map((c) => c.name).join(", ")}</td>
                  <td>{book?.authors?.map((a) => a.name).join(", ")}</td>
                  <td>{book.pageNumbers}</td>
                  <td>{book.price.toLocaleString("en")}đ</td>
                  <td>{book.publishingYear}</td>
                  <td>{book.quantity}</td>
                  <td>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleDeleteBook(book.id)}
                    >
                      Xóa
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      <nav className="tm-gallery-nav">
        <Container className="nav justify-content-center">
          <button
            className="btn btn button-categories btn-outline-success"
            onClick={() => (page > 1 ? setPage(page - 1) : setPage(1))}
            style={{ marginLeft: "5px" }}
          >
            <i className="fa-solid fa-angle-left"></i>
          </button>
          {pageDisplay.length > 0 &&
            pageDisplay.map((p) => (
              <button
                key={p}
                className={`btn btn button-categories ${
                  +page === +p ? "btn-success" : "btn-outline-success"
                }`}
                onClick={() => setPage(p)}
                style={{ marginLeft: "5px" }}
              >
                {p}
              </button>
            ))}

          <button
            className="btn btn button-categories btn-outline-success"
            onClick={() =>
              page < totalPages ? setPage(page + 1) : setPage(totalPages)
            }
            style={{ marginLeft: "5px" }}
          >
            <i className="fa-solid fa-angle-right"></i>
          </button>
        </Container>
      </nav>
    </div>
  );
}

export default BookAdminList;
