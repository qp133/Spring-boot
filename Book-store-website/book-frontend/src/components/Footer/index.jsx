import React from 'react'
import { Link } from 'react-router-dom'
function Footer() {
  return (
    <>
         <section className="media tm-highlight tm-highlight-w-icon">
        <div className="tm-highlight-icon">
          <i className="fa tm-fa-6x fa-meetup"></i>
        </div>

        <div className="media-body">
          <header>
            <h2>Need Help?</h2>
          </header>
          <p className="tm-margin-b">
            Mở rộng tư duy, giàu kiến thức và tăng cường sức khỏe trí tuệ của
            bạn với mỗi trang sách được đọc.
          </p>
          <Link to={"/contact"} className="tm-white-bordered-btn">
            Live Chat
          </Link>
        </div>
      </section>
    </>
  )
}

export default Footer