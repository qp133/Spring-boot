import React from "react";

function Contact() {
  return (
    <>
      <main id="main" className="main">
        <div className="pagetitle">
          <h1>Mọi vấn đề xin liên hệ</h1>
        
        </div>

        <section className="section profile">
          <div className="row">
            <div className="col-xl-4">
              <div className="card">
                <div className="card-body profile-card pt-4 d-flex flex-column align-items-center">
                  <img
                    src="https://scontent.fhan14-3.fna.fbcdn.net/v/t1.18169-9/24852589_365622133863182_7606245839217546982_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=oMPpUvWuu_IAX-5CH_c&_nc_ht=scontent.fhan14-3.fna&oh=00_AfD4aSytVHCzNUStgdlFq-IHJgVQM55GqC7YITd04jnMgA&oe=6437C30E"
                    alt="Profile"
                    className="rounded-circle"
                  />
                  <h2>Nguyễn Văn Quang</h2>
                  <h3>Web Designer</h3>
                  <div className="social-links mt-2">
                    <a href="https://github.com/nguyenvanquang97" className="github">
                    <i className="fa-brands fa-github"></i>
                    </a>
                    <a href="https://www.facebook.com/profile.php?id=100012461946460" className="facebook">
                    <i className="fa-brands fa-facebook"></i>
                    </a>
                    <a href="https://www.facebook.com/profile.php?id=100012461946460" className="messenger">
                    <i className="fa-brands fa-facebook-messenger"></i>
                    </a>
                   
                  </div>
                </div>
              </div>
            </div>

            <div className="col-xl-8">
              <div className="card">
                <div className="card-body pt-3">
                  <ul className="nav nav-tabs nav-tabs-bordered">
                    <li className="nav-item">
                      <button
                        className="nav-link active"
                        data-bs-toggle="tab"
                        data-bs-target="#profile-overview"
                      >
                        Overview
                      </button>
                    </li>
                  </ul>
                  <div className="tab-content pt-2">
                    <div
                      className="tab-pane fade show active profile-overview"
                      id="profile-overview"
                    >
                      <h5 className="card-title">About</h5>
                      <p className="small fst-italic">
                        Được ông anh 96 cơ khí bách khoa rủ chuyển qua lập trình
                      </p>

                      <h5 className="card-title">Profile Details</h5>

                      <div className="row">
                        <div className="col-lg-3 col-md-4 label ">Full Name</div>
                        <div className="col-lg-9 col-md-8">Nguyễn Văn Quang</div>
                      </div>

              

                      <div className="row">
                        <div className="col-lg-3 col-md-4 label">Job</div>
                        <div className="col-lg-9 col-md-8">Web Designer</div>
                      </div>

                      <div className="row">
                        <div className="col-lg-3 col-md-4 label">Country</div>
                        <div className="col-lg-9 col-md-8">Việt Nam</div>
                      </div>

                      <div className="row">
                        <div className="col-lg-3 col-md-4 label">Address</div>
                        <div className="col-lg-9 col-md-8">
                          Hà Nội,Việt Nam
                        </div>
                      </div>

                      <div className="row">
                        <div className="col-lg-3 col-md-4 label">Phone</div>
                        <div className="col-lg-9 col-md-8">
                          (+84) 384 162 1xx
                        </div>
                      </div>

                      <div className="row">
                        <div className="col-lg-3 col-md-4 label">Email</div>
                        <div className="col-lg-9 col-md-8">
                          quangdtkc@gmail.com
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </main>
    </>
  );
}

export default Contact;
