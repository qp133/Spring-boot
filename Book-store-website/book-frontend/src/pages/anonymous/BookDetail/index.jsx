import React, { useEffect, useState } from "react";
import bookApi from "../../../app/api/bookApi";
import { Link, useParams } from "react-router-dom";
import commentApi from "../../../app/api/commentApi";
import { convertDate } from "../../../utils/utils";
import { useCreateOrderItemMutation } from "../../../app/services/orderItemService";
import { ToastContainer, toast } from "react-toastify";
import { useSelector } from "react-redux";
import { usePostCommentMutation } from "../../../app/services/orderUserService";
function BookDetail() {
  const { bookId } = useParams();
  const [book, setBook] = useState({});
  const [bookByAuthor, setBookByAuthor] = useState([]);
  const [comments, setComments] = useState([]);
  const [newComment,setNewComment]=useState("")
  const [createOrderItem] = useCreateOrderItemMutation();
  const [amount, setAmount] = useState(1);
  const { auth } = useSelector((state) => state.auth);
  const [postComment]=usePostCommentMutation();
  const [renderComment,setRenderComment]=useState(true)
  // Lấy danh sách bài viết
  useEffect(() => {
    const fetchBook = async () => {
      try {
        const res = await bookApi.getBookById(bookId);
        setBook(res.data);
        console.log(res.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchBook();
  }, [bookId]);

  useEffect(() => {
    const fetchComment = async () => {
      try {
        const res = await commentApi.getComments(bookId);
        setComments(res.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchComment();
  }, [renderComment,bookId]);
  useEffect(() => {
    const fetchBookByAuthor = async () => {
      if (book.id) {
        try {
          const res = await bookApi.getBookByAuthorid(book.authors[0].id);
          setBookByAuthor(res.data);
          console.log(res.data);
        } catch (error) {
          console.log(error);
        }
      }
    };

    fetchBookByAuthor();
  }, [book]);
  let sumPrice = book.price * amount;

  const tru = () => {
    if (amount <= 1) {
      alert("số lượng không thể nhỏ hơn 1");
    } else {
      setAmount(amount - 1);
    }
  };
  const cong = () => {
    setAmount(amount + 1);
  };
 console.log(book)
  const handleAddOrderItem = () => {
    let newOrderItem = {
      book: book,
      amount: amount,
      user: auth,
    };
    console.log(newOrderItem);
    createOrderItem(newOrderItem)
      .unwrap()
      .then(() => {
        toast.success("Đã thêm vào giỏ hàng");
      })
      .catch((err) => alert(err.data.message));
  };
 
  const handlePostComment=()=>{
    let id=auth.id;
    postComment({bookId:bookId,userId:id,content:newComment})
    .unwrap()
    .then(() => {
      toast.success("Bình luận thành công");
      setNewComment("")
      setRenderComment(!renderComment);
    
    })
    .catch((err) => alert(err.data.message));
   
  }
  return (
    <div className="tm-main-content no-pad-b">
      <section className="row tm-item-preview ">
        <div className="col-md-6 col-sm-12 mb-md-0 mb-5 ">
          <img
            src={
              book?.thumbnail ??
              "https://product.hstatic.net/1000363117/product/tb30-1_7a670519dc344827af0cc77ba6c15afa_master.jpg"
            }
            alt="bookThumbnail"
            className="img-fluid tm-img-center-sm image-bookDetail"
          />
        </div>
        <div className="col-md-6 col-sm-12 infor-bookDetail">
          <h2 className="tm-blue-text">{book.title}</h2>
          <p>Mô tả:{book.description}</p>
          <p>Tác giả:{book.authors?.map((a) => (
            <Link to={`/books/author/${a.id}/${a.name}`}>{a.name}</Link>
          ))}</p>

          <p>Thể loại:{book.categories?.map((c) => c.name).join(", ")}</p>

          <p>Năm xuất bản:{book.publishingYear}</p>
          <p>Số trang:{book.pageNumbers}</p>
          <p className="price">{sumPrice.toLocaleString("en")} đ</p>
          <div className="d-inline-block me-3">
            <button
              className="border py-2 px-3 d-inline-block fw-bold bg-light"
              onClick={() => tru()}
            >
              -
            </button>
            <span className="py-2 px-3 d-inline-block fw-bold count ">
              {amount}
            </span>
            <button
              className="border py-2 px-3 d-inline-block fw-bold bg-light"
              onClick={() => cong()}
            >
              +
            </button>
          </div>
          <div>
            <button
              onClick={handleAddOrderItem}
              className="tm-btn tm-btn-gray tm-margin-r-20 "
            >
              Thêm vào giỏ
            </button>
            <Link
              to={"/user/shopping-cart"}
              href="#"
              className="tm-btn tm-btn-blue"
            >
              Mua ngay
            </Link>
          </div>
        </div>
      </section>

      <div className="tm-gallery no-pad-b">
        <h4 style={{ marginTop: "40px" }}>Sách cùng tác giả</h4>
        <div className="row book-equal-author">
          {bookByAuthor?.map(
            (book, index) =>
              index < 6 && (
                <figure
                  key={book.id}
                  className="col-lg-2 col-md-4 col-sm-6 col-12 tm-gallery-item mb-5"
                >
                  <Link to={`/books/${book.id}/${book.slug}`}>
                    <div className="image-book">
                      <img
                        src={book?.thumbnail}
                        alt="book"
                        className="img-fluid tm-img-center"
                      />
                    </div>
                    <p className="tm-figcaption no-pad-b">{book?.title}</p>
                  </Link>
                </figure>
              )
          )}
        </div>
      </div>
      <div>
        <h4>Bình luận</h4>
        <hr />
        <p style={{marginBottom:"30px"}}>{comments.length} bình luận</p>
        {auth?.id&&(
          <div style={{ marginBottom: "10px" }} >
          <span className="infor-user-comment">
            {" "}
            <img
              src={
                auth?.avatar
                  ? `${auth?.avatar}`
                  : "https://via.placeholder.com/150"
              }
              className="rounded-circle"
              style={{ width: "40px" }}
              alt={auth?.name}
            />{" "}
            <p style={{ marginBottom: "7px" }}>{auth?.name}</p>
          </span>
          <textarea
              className="form-control"
              id="course-description"
              rows="2"
              value={newComment}
              style={{"width":"500px"}}
              placeholder="Viết bình luận"
              onChange={(e)=>setNewComment(e.target.value)}
              onKeyDown={(e) => {
                if (e.key === "Enter") {
                  handlePostComment();
                }
              }}
            ></textarea>
        </div>
        )
        }
        {!auth?.id&&(
          <p>*Đăng nhập để bình luận</p>
        )}
      
        {comments.length > 0 &&
          comments.map((comment) => (
            <div style={{ marginBottom: "10px" }} key={comment.id}>
              <span className="infor-user-comment">
                {" "}
                <img
                  src={
                    comment.user?.avatar
                      ? `${comment.user?.avatar}`
                      : "https://via.placeholder.com/150"
                  }
                  className="rounded-circle"
                  style={{ width: "40px" }}
                  alt={comment.user?.name}
                />{" "}
                <p style={{ marginBottom: "7px" }}>{comment.user.name}</p>
              </span>
              <div className="comment">
                <p>{comment.content}</p>
              </div>
              <p>{convertDate(comment.createdAt)}</p>
            </div>
          ))}
      </div>
      <ToastContainer position="top-center" />
    </div>
  );
}

export default BookDetail;
