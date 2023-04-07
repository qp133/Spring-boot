import React, { useState } from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import Select from "react-select";
import { toast, ToastContainer } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { useGetAuthorsQuery } from "../../../../app/services/authorSevice";

import {
 
  useCreateBookMutation,
} from "../../../../app/services/bookService";
import { useGetCategoriesQuery } from "../../../../app/services/categoryService";

function BookAdminCreate() {
  const [quantity, setQuantity] = useState(10);
  const [title, setTitle] = useState("");
  const [price, setPrice] = useState(100000);
  const [publishingYear, setPublishingYear] = useState(2023);
  const [pageNumbers, setPageNumbers] = useState(500);
  const [description, setDescription] = useState("");
  const [categoryIds, setCategoryIds] = useState([]);
  const [authorIds, setAuthorIds] = useState([]);
  const { authors } = useSelector((state) => state.authors);
  const { categories } = useSelector((state) => state.categories);
  const { isLoadingCategories } = useGetCategoriesQuery();
  const {isLoadingAuthors}=useGetAuthorsQuery();
  const [createBook] = useCreateBookMutation();
  const navigate=useNavigate();
  const optionsCategories = categories.map((category) => {
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


  const handleChangeCategory = async (e) => {
    let arr = [];
    for (let i = 0; i < e.length; i++) {
      arr.push(e[i].value);
    }

    setCategoryIds(arr);
  };
  const handleChangeAuthor = async (e) => {
    let arr = [];
    for (let i = 0; i < e.length; i++) {
      arr.push(e[i].value);
    }
    setAuthorIds(arr);
  };
  const handleCreateBook = () => {
    let newBook = {
      title: title,
      description: description,
      pageNumbers:pageNumbers,
      price:price,
      categoryIds: categoryIds,
      authorIds:authorIds,
      thumbnail:"http://3.bp.blogspot.com/-YD-_AsQtHyQ/Vmmg50MZXHI/AAAAAAAAAcI/d6-wtz5NqdM/w1200-h630-p-k-no-nu/anh-bia-facbook-doc-cho-nhung-ban-ca-tinh-1.jpg",
      publishingYear:publishingYear,
      quantity: quantity
    };
    createBook(newBook)
    .unwrap()
    .then(() => {
    toast.success("Thêm sách mới thành công")

      setTimeout(() => {
          navigate("/admin/books");
      }, 1500);
    })
    .catch((err) => alert(err));

  };

  if (isLoadingAuthors||isLoadingCategories) {
    return <h3>Loading ...</h3>;
  }

  return (
    <div className="course-list mt-4 mb-4">
      <div className="container">
        <div className="mb-4">
          <button
            className="btn btn-success"
            onClick={handleCreateBook}
          >
            <span>
              <i className="fa-solid fa-plus"></i>
            </span>
            Tạo
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
                  value={title}
                  onChange={(e) => setTitle(e.target.value)}
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
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
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
                  value={pageNumbers}
                  onChange={(e) => setPageNumbers(e.target.value)}
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
                  value={publishingYear}
                  onChange={(e) => setPublishingYear(e.target.value)}
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
                  value={price}
                  onChange={(e) => setPrice(e.target.value)}
                ></textarea>
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
                  id="course-description"
                  rows="1"
                  value={quantity}
                  onChange={(e) => setQuantity(e.target.value)}
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
                />
              </div>
              <div className="mb-3">
                <label htmlFor="course-topic" className="form-label fw-bold">
                  Thể loại
                </label>

                <Select
                  closeMenuOnSelect={false}
                  onChange={handleChangeCategory}
                  options={optionsCategories}
                  isMulti
                />
              </div>
  
            </div>
          </div>
        </div>
      </div>
      <ToastContainer position="top-center" />
    </div>
  );
}

export default BookAdminCreate;
