import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import Select from "react-select";
import { useSelector } from "react-redux";
import { useGetAuthorsQuery } from "../../../../app/services/authorSevice";
import { useGetCategoriesQuery } from "../../../../app/services/categoryService";
import {
  useUpdateBookMutation,
  useUploadThumbnailMutation,
} from "../../../../app/services/bookService";

function BookAdminDetail() {
  const navigate = useNavigate();
  const { bookId } = useParams();
  const { books } = useSelector((state) => state.books);
  const { authors } = useSelector((state) => state.authors);
  const { categories } = useSelector((state) => state.categories);
  const [categoryIds, setCategoryIds] = useState([]);
  const [authorIds, setAuthorIds] = useState([]);
  const [editBook] = useUpdateBookMutation();
  const [uploadThumbnail] = useUploadThumbnailMutation();
  useGetCategoriesQuery();
  useGetAuthorsQuery();
  const [book, setBook] = useState(() => {
    console.log(bookId);
    return books.find((b) => b.id === +bookId);
  });
  console.log(book);
  const [file, setFile] = useState(null);
  useEffect(() => {
    const fetchBookByAuthor = async () => {
      if (book?.id) {
        try {
          setAuthorIds(
            book.authors.map((a) => {
              return a.id;
            })
          );
          setCategoryIds(
            book.categories.map((c) => {
              return c.id;
            })
          );
        } catch (error) {
          console.log(error);
        }
      }
    };

    fetchBookByAuthor();
  }, [book]);
  const optionsCategories = categories.map((category) => {
    return {
      value: category.id,
      label: category.name,
    };
  });
  const defaultValueCategories = book?.categories.map((category) => {
    return {
      value: category.id,
      label: category.name,
    };
  });
  const optionsAuthors = authors.map((author) => {
    return {
      value: author.id,
      label: author.name,
    };
  });
  const defaultValueAuthors = book?.authors?.map((author) => {
    return {
      value: author.id,
      label: author.name,
    };
  });
  const handleUpdateBook = () => {
    if (book.title === null) {
      alert("Tiêu đề không được để trống");
      return;
    }
    if (book.description === null) {
      alert("Mô tả không được để trống");
      return;
    }
    if (book.pageNumbers === null) {
      alert("Số trang không được để trống");
      return;
    }
    if (book.publishingYear === null) {
      alert("Năm xuất bản không được để trống");
      return;
    }
    if (book.price === null) {
      alert("Giá tiền không được để trống");
      return;
    }
    if (book.quantity === null) {
      alert("Số lượng không được để trống");
      return;
    }
    editBook({
      id: bookId,
      title: book.title,
      description: book.description,
      pageNumbers: +book.pageNumbers,
      publishingYear: +book.publishingYear,
      price: +book.price,
      authorIds: authorIds,
      categoryIds: categoryIds,
      quantity: +book.quantity,
    })
      .unwrap()
      .then(() => {
        toast.success("Cập nhật sách thành công");

        setTimeout(() => {
          navigate("/admin/books");
        }, 1500);
      })
      .catch((err) => alert(err));
  };
  const handleChangeAuthor = (e) => {
    let arr = [];
    for (let i = 0; i < e.length; i++) {
      arr.push(e[i].value);
    }
    setAuthorIds(arr);
  };
  const handleChangeCategory = (e) => {
    let arr = [];
    for (let i = 0; i < e.length; i++) {
      arr.push(e[i].value);
    }

    setCategoryIds(arr);
  };
  const handleOnChange = (e) => {
    setFile(e.target.files[0]);
  };
  const handleSubmit = (event) => {
    event.preventDefault();

    const formData = new FormData();
    formData.append("file", file);
    console.log(file);
    uploadThumbnail({ id: bookId, file: formData })
      .unwrap()
      .then((res) => {
        toast.success("Cập nhật ảnh bìa thành công");
        setTimeout(() => {
          navigate(`/admin/books`);
        }, 1500);
      })
      .catch((err) => console.log(err));
  };
  return (
    <div className="course-list mt-4 mb-4">
      <div className="container">
        <div className="mb-4">
          <button className="btn btn-success" onClick={handleUpdateBook}>
            <span>
              <i className="fa-solid fa-plus"></i>
            </span>
            Update
          </button>
          <Link to={"/admin/books"} className="btn-custom btn-refresh">
            <span>
              <i className="fa-solid fa-angle-left"></i>
            </span>
            Quay lại
          </Link>
        </div>

        <div className="course-list-inner p-2">
          <div className="row">
            <div className="col-md-8">
              <div className="mb-3">
                <label htmlFor="course-name" className="form-label fw-bold">
                  Tiêu đề
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="course-name"
                  value={book?.title || ""}
                  onChange={(e) => setBook({ ...book, title: e.target.value })}
                />
              </div>

              <div className="mb-3">
                <label
                  htmlFor="course-description"
                  className="form-label fw-bold"
                >
                  Mô tả
                </label>
                <textarea
                  className="form-control"
                  id="course-description"
                  rows="5"
                  value={book?.description || ""}
                  onChange={(e) =>
                    setBook({ ...book, description: e.target.value })
                  }
                ></textarea>
              </div>
              <div className="mb-3">
                <label
                  htmlFor="course-description"
                  className="form-label fw-bold"
                >
                  Số trang
                </label>
                <textarea
                  className="form-control"
                  id="course-description"
                  rows="1"
                  value={book?.pageNumbers || ""}
                  onChange={(e) =>
                    setBook({ ...book, pageNumbers: e.target.value })
                  }
                ></textarea>
              </div>
              <div className="mb-3">
                <label
                  htmlFor="course-description"
                  className="form-label fw-bold"
                >
                  Năm phát hành
                </label>
                <textarea
                  className="form-control"
                  id="course-description"
                  rows="1"
                  value={book?.publishingYear || ""}
                  onChange={(e) =>
                    setBook({ ...book, publishingYear: e.target.value })
                  }
                ></textarea>
              </div>
              <div className="mb-3">
                <label
                  htmlFor="course-description"
                  className="form-label fw-bold"
                >
                  Giá
                </label>
                <textarea
                  className="form-control"
                  id="course-description"
                  rows="1"
                  value={book?.price || ""}
                  onChange={(e) => setBook({ ...book, price: e.target.value })}
                ></textarea>
              </div>
            </div>
            <div className="col-md-4">
              <div className="mb-3">
                <label htmlFor="course-type" className="form-label fw-bold">
                  Tác giả
                </label>
                <Select
                  onChange={handleChangeAuthor}
                  options={optionsAuthors}
                  isMulti
                  defaultValue={defaultValueAuthors}
                />
              </div>
              <div className="mb-3">
                <label
                  htmlFor="course-description"
                  className="form-label fw-bold"
                >
                  Số lượng
                </label>
                <textarea
                  className="form-control"
                  id="course-quantity"
                  rows="1"
                  value={book?.quantity}
                  onChange={(e) => setBook({ ...book, quantity: e.target.value })}
                ></textarea>
              </div>
              <div className="mb-3">
                <label htmlFor="course-topic" className="form-label fw-bold">
                  Thể loại
                </label>

                <Select
                  closeMenuOnSelect={false}
                  onChange={handleChangeCategory}
                  options={optionsCategories}
                  defaultValue={defaultValueCategories}
                  isMulti
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Thumbnail</label>
                <div className="avatar-preview mb-3 rounded"></div>
                <form onSubmit={handleSubmit}>
                  <label htmlFor="avatar" className="form-thumbnail">
                    <img
                      src={
                        book?.thumbnail
                          ? `${book?.thumbnail}`
                          : "https://via.placeholder.com/150"
                      }
                      alt="thumbnail"
                      id="thumbnail-preview"

                      // style={{ width: "150px" }}
                    />
                  </label>
                  <input
                    type="file"
                    onChange={handleOnChange}
                    id="avatar"
                    className="d-none"
                  />
                  <button className="btn btn-success" type="submit">
                    Xác nhận
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      <ToastContainer position="top-center" />
    </div>
  );
}

export default BookAdminDetail;
