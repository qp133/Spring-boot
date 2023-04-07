import React, { useEffect, useState } from "react";
import bookApi from "../../../app/api/bookApi";
import categoryApi from "../../../app/api/categoryApi";
import queryString from "query-string";
import { Link, useLocation, useNavigate } from "react-router-dom";
import {
  Pagination,
  PaginationItem,
  PaginationLink,
  Container,
} from "reactstrap";

function BookList() {
  const location = useLocation();
  const navigate = useNavigate();

  const [books, setBooks] = useState([]);
  const [categoriess, setCategoriess] = useState([]);
  const [term, setTerm] = useState("");
  const [totalPages, setTotalPages] = useState();
  const [bestSellerBooks, setBestSellerBooks] = useState([]);
  const [page, setPage] = useState(1);
  // Khởi tạo state ban đầu dựa trên url hiện tại
  const [filter, setFilter] = useState(() => {
    const params = queryString.parse(location.search);
    return {
      search: params.search || "",
      category: params.category || "",
      page: params.page || "",
    };
  });
  console.log(filter);
  // Khi url thay đổi => parse lại url => lưu vào state
  useEffect(() => {
    const params = queryString.parse(location.search);
    setFilter({
      search: params.search || "",
      category: params.category || "",
      page: params.page || "",
    });
  }, [location.search]);

  const pageDisplay = [];
  for (let i = 1; i <= totalPages; i++) {
    pageDisplay.push(i);
  }
  // Lấy danh sách bài viết
  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const query = queryString.stringify(filter, {
          skipEmptyString: true,
        }); // category=sylas&name=abc
        const res = await bookApi.getBooks(query);
        console.log(res.data);
        setBooks(res.data.content);
        setTotalPages(res.data.totalPages);
      } catch (error) {
        console.log(error);
      }
    };

    fetchBooks();
  }, [filter]);
  //lấy ra sách ngẫu nhiên
  useEffect(() => {
    const fetchBestSellerBook = async () => {
      try {
        const query = queryString.stringify({
          skipEmptyString: true,
        });
        const res = await bookApi.getBooks(query);
        const copy = [];
        // console.log(res.data)
        for (let i = 0; i < res.data.content.length; i++) {
          let randomBook =
            res.data.content[
              Math.floor(Math.random() * res.data.content.length)
            ];
          if (!copy.includes(randomBook)) {
            copy.push(randomBook);
          }
          if (copy.length === 6) {
            break;
          }
        }
        setBestSellerBooks(copy);
      } catch (error) {
        console.log(error);
      }
    };

    fetchBestSellerBook();
  }, []);

  // Lấy danh sách category
  useEffect(() => {
    const fetchCategoies = async () => {
      try {
        let res = await categoryApi.getCategories();
        setCategoriess(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchCategoies();
  }, []);

  // Lấy danh sách theo page
  useEffect(() => {
    const handlePageChange = () => {
      const params = { ...filter, page: page };

      navigate({
        pathname: location.pathname, // http://localhost:3000
        search: queryString.stringify(params, {
          // category=sylas&name=abc
          skipEmptyString: true,
        }),
      });
    };
    handlePageChange();
  }, [page]);
  // Lọc theo category
  const filterByCategory = (name) => {
    const params = { ...filter, category: name, page: 1 };

    navigate({
      pathname: location.pathname, // http://localhost:3000
      search: queryString.stringify(params, {
        // category=sylas&name=abc
        skipEmptyString: true,
      }),
    });
  };
  //lọc theo search
  const handleSearch = () => {
    const params = { ...filter, search: term, page: 1 };
    navigate({
      pathname: location.pathname, // http://localhost:3000
      search: queryString.stringify(params, {
        // category=sylas&name=abc
        skipEmptyString: true,
      }),
    });
  };

  return (
    <div className="tm-main-content">
      <section className="tm-margin-b-l">
        <header>
          <h2
            style={{ textAlign: "center" }}
            className="tm-main-title bestSeller-title"
          >
            Sách bán chạy nhất
          </h2>
        </header>

        <div className="tm-gallery">
          {books.length > 0 &&
            bestSellerBooks.map((bestSellerBook, index) => (
              <figure
                key={index}
                className="col-lg-2 col-md-4 col-sm-6 col-12 tm-gallery-item book-cart"
              >
                <Link to={`/books/${bestSellerBook.id}/${bestSellerBook.slug}`}>
                  <div>
                    <div className="sale-book">
                      -{Math.floor(Math.random() * 31) + 10}%
                    </div>
                    <div className="tm-gallery-item-overlay">
                      <div className="image-book">
                        <img
                          src={bestSellerBook?.thumbnail}
                          alt="bestSellerThumbnail"
                        />
                      </div>
                    </div>
                  </div>
                  <p className="tm-figcaption">{bestSellerBook.title}</p>
                  <p className="book-price">
                    {bestSellerBook.price.toLocaleString("en")} đ
                  </p>
                  <div className="infor-book">
                    <div className="rainbow">
                      <p>
                        Tác giả:
                        {bestSellerBook?.authors?.map((a) => a.name).join(", ")}
                      </p>
                      <p>
                        Thể loại:
                        {bestSellerBook?.categories
                          ?.map((c) => c.name)
                          .join(", ")}
                      </p>
                      <p>Số trang:{bestSellerBook.pageNumbers}</p>
                    </div>
                  </div>
                </Link>
              </figure>
            ))}
          <hr
            style={{ marginTop: "70px" }}
            className="separator separator--line"
          />
          <h2 style={{ textAlign: "center" }}>Tất cả sách</h2>
          <div style={{ marginTop: "30px" }} className="search-book ">
            <div className="col-md-6">
              <div className="seach-form d-flex align-items-center rounded shadow-sm mb-4 pe-3">
                <input
                  type="text"
                  placeholder="Tìm kiếm"
                  className="form-control border-0 seach-form-input"
                  value={term}
                  onKeyDown={(e) => {
                    if (e.key === "Enter") {
                      handleSearch();
                    }
                  }}
                  onChange={(e) => setTerm(e.target.value)}
                />
                <span className="text-black-50 seach-form-button">
                  <i className="fa-solid fa-magnifying-glass"></i>
                </span>
              </div>

              <div className="mb-4">
                <button
                  className={`btn btn button-categories ${
                    filter.category === ""
                      ? "btn-success"
                      : "btn-outline-success"
                  }`}
                  onClick={() => filterByCategory("")}
                >
                  Tất cả thể loại
                </button>
                {categoriess.length > 0 &&
                  categoriess.map((category) => (
                    <button
                      key={category.id}
                      className={`btn btn button-categories ${
                        filter.category === category.name
                          ? "btn-success"
                          : "btn-outline-success"
                      }`}
                      onClick={() => filterByCategory(category.name)}
                    >
                      {category.name}
                    </button>
                  ))}
              </div>
            </div>
          </div>
          <div className="row">
            {books.length > 0 &&
              books?.map((book) => (
                <figure
                  key={book.id}
                  className="col-lg-2 col-md-4 col-sm-6 col-12 tm-gallery-item book-cart"
                >
                  <Link to={`/books/${book.id}/${book.slug}`}>
                    <div>
                      <div className="sale-book">
                        -{Math.floor(Math.random() * 31) + 10}%
                      </div>
                      <div className="tm-gallery-item-overlay">
                        <div className="image-book">
                          <img src={book?.thumbnail} alt="bookThumbnail" />
                        </div>
                      </div>
                    </div>
                    <p className="tm-figcaption">{book.title}</p>
                    <p className="book-price">
                      {book.price.toLocaleString("en")} đ
                    </p>
                    <div className="infor-book">
                      <div className="rainbow">
                        <p>
                          Tác giả:{book?.authors?.map((a) => a.name).join(", ")}
                        </p>
                        <p>
                          Thể loại:
                          {book?.categories?.map((c) => c.name).join(", ")}
                        </p>
                        <p>Số trang:{book.pageNumbers}</p>
                      </div>
                    </div>
                  </Link>
                </figure>
              ))}
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
              pageDisplay.map((page) => (
                <button
                  key={page}
                  className={`btn btn button-categories ${
                    +filter.page === +page
                      ? "btn-success"
                      : "btn-outline-success"
                  }`}
                  onClick={() => setPage(page)}
                  style={{ marginLeft: "5px" }}
                >
                  {page}
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
      </section>

  
    </div>
  );
}

export default BookList;
