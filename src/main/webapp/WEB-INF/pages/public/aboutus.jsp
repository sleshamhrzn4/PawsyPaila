<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - Pawsy Paila</title>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aboutus.css">
</head>
<body>

    <jsp:include page="header.jsp" /> 

    <main class="about-container">
        
        <section class="story-section">
            <h1 class="section-title">Our Story</h1>
            
            <div class="story-grid">
                <div class="text-col">
                    <div class="card belief-card">
                        <p>At Pawsy Paila, we believe that every pet deserves a loving family.</p>
                    </div>
                    <div class="card sub-card">
                        <p>Our platform was created with a mission to connect animals in need with caring people, while also making pet care easy, accessible, and enjoyable.</p>
                    </div>
                </div>
                <div class="image-col">
                    <div class="blue-backdrop">
                        <img src="${pageContext.request.contextPath}/images/about-cat.png" alt="Cat" class="pop-out-img cat-fix">
                    </div>
                </div>
            </div>

            <div class="story-grid reverse">
                <div class="image-col">
                    <div class="blue-backdrop">
                        <img src="${pageContext.request.contextPath}/images/about-dog.png" alt="Dog" class="pop-out-img dog-fix">
                    </div>
                </div>
                <div class="text-col">
                    <div class="card sub-card">
                        <p>Alongside adoption, we also provide a wide range of pet products. From nutritious food to fun toys and essential accessories, so you can give your pets the love and care they deserve.</p>
                    </div>
                    <div class="card sub-card">
                        <p>At our core, we stand for compassion, responsibility, and the belief that every small step can make a big difference in an animal's life.</p>
                    </div>
                </div>
            </div>
        </section>

        <section class="team-section">
            <h1 class="section-title">Our Team</h1>
            
            <div class="team-grid top-row">
                <div class="member">
                    <div class="member-img-box"><img src="${pageContext.request.contextPath}/images/slesha.png" alt="Slesha"></div>
                    <h3>Slesha Maharjan</h3>
                    <p>Head Director</p>
                </div>
                <div class="member">
                    <div class="member-img-box"><img src="${pageContext.request.contextPath}/images/yunisha.png" alt="Yunisha"></div>
                    <h3>Yunisha Basnet</h3>
                    <p>Head of Product</p>
                </div>
                <div class="member">
                    <div class="member-img-box"><img src="${pageContext.request.contextPath}/images/swornima.png" alt="Swornima"></div>
                    <h3>Swornima Maharjan</h3>
                    <p>Managing Director</p>
                </div>
            </div>

            <div class="team-grid bottom-row">
                <div class="member">
                    <div class="member-img-box"><img src="${pageContext.request.contextPath}/images/sulove.png" alt="Sulove"></div>
                    <h3>Sulove Noko Shrestha</h3>
                    <p>Head of Creative</p>
                </div>
                <div class="member">
                    <div class="member-img-box"><img src="${pageContext.request.contextPath}/images/swoyam.png" alt="Swoyam"></div>
                    <h3>Swoyam Maharjan</h3>
                    <p>Director of Sales</p>
                </div>
            </div>
        </section>

    </main>
</body>
</html>