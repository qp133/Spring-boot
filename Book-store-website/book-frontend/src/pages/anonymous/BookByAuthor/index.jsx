import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import bookApi from "../../../app/api/bookApi";
import { Link } from "react-router-dom";
function BookByAuthor() {
  const { authorId ,authorName} = useParams();
  const [bookByAuthor,setBookByAuthor]=useState()
    useEffect(() => {
    const fetchBookByAuthor = async () => {

        try {
          const res = await bookApi.getBookByAuthorid(authorId);
          setBookByAuthor(res.data);
          console.log(res.data);
        } catch (error) {
          console.log(error);
        }   
    };

    fetchBookByAuthor();
  }, [authorId]);
  console.log(authorName)
  return (
    <div>
      <h3 style={{marginTop:"30px",marginBottom:"50px"}}>Tất cả sách của tác giả {authorName}</h3>
      <div className="row">
            {bookByAuthor?.length > 0 &&
              bookByAuthor?.map((book) => (
                <figure
                  key={book.id}
                  className="col-lg-2 col-md-4 col-sm-6 col-10 tm-gallery-item book-cart"
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
  )
}

export default BookByAuthor