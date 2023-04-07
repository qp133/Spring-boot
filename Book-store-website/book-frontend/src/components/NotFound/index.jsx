import React from 'react'
import { Link } from 'react-router-dom'

function NotFound() {
  return (
    <div className="container">

    <section className="section error-404 min-vh-100 d-flex flex-column align-items-center justify-content-center">
      <h1>404</h1>
      <h2>The page you are looking for doesn't exist.</h2>
      <Link to={"/"} className="btn">Back to home</Link>
      <img src="https://www.pe-center.com/img/not_found.svg" className="img-fluid py-5" alt="Page Not Found"/>
      <div className="credits">
        Designed by <a href="https://www.facebook.com/profile.php?id=100012461946460://bootstrapmade.com/">A Quang</a>
      </div>
    </section>

  </div>
  )
}

export default NotFound