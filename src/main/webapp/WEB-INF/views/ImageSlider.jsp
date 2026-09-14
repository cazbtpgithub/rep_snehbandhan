<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
/* Premium Gallery CSS (White Theme) */
.premium-gallery-wrapper {
    background: #ffffff;
    border-radius: 16px;
    padding: 35px;
    font-family: 'Inter', 'Segoe UI', sans-serif;
    color: #333333;
    box-shadow: 0 15px 35px rgba(0,0,0,0.1), 0 5px 15px rgba(0,0,0,0.05);
    border: 1px solid rgba(0,0,0,0.05);
    position: relative;
    overflow: hidden;
}

.premium-gallery-wrapper::before {
    content: '';
    position: absolute;
    top: -50%; left: -50%; width: 200%; height: 200%;
    background: radial-gradient(circle, rgba(210,55,121,0.06) 0%, rgba(255,255,255,0) 60%);
    pointer-events: none;
    z-index: 0;
}

.premium-gallery-header {
    text-align: center;
    margin-bottom: 35px;
    position: relative;
    z-index: 1;
}

.premium-gallery-header h3 {
    margin: 0;
    font-size: 32px;
    font-weight: 700;
    background: linear-gradient(135deg, #d23779, #ff9a9e);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    letter-spacing: 1.5px;
    text-transform: uppercase;
}

.premium-gallery-header p {
    color: #777777;
    font-size: 15px;
    margin-top: 10px;
    font-weight: 500;
    letter-spacing: 0.5px;
}

.premium-gallery-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
    gap: 25px;
    position: relative;
    z-index: 1;
}

.premium-gallery-item {
    position: relative;
    border-radius: 18px;
    overflow: hidden;
    cursor: pointer;
    box-shadow: 0 10px 20px rgba(0,0,0,0.12);
    transition: all 0.5s cubic-bezier(0.25, 0.8, 0.25, 1);
    aspect-ratio: 3 / 4;
    display: block;
    background: #f8f9fa;
    border: 1px solid rgba(0,0,0,0.05);
}

.premium-gallery-item:hover {
    transform: scale(1.06) translateY(-8px);
    box-shadow: 0 20px 35px rgba(210, 55, 121, 0.25), 0 0 15px rgba(210, 55, 121, 0.1);
    z-index: 10;
}

.premium-gallery-item img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: top center;
    transition: transform 0.8s ease, opacity 0.5s;
    opacity: 0.95;
}

.premium-gallery-item:hover img {
    transform: scale(1.15);
    opacity: 1;
}

.premium-item-overlay {
    position: absolute;
    top: 0; left: 0; right: 0; bottom: 0;
    background: linear-gradient(to bottom, rgba(255,255,255,0.1) 0%, rgba(255,255,255,0.95) 100%);
    opacity: 0;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    align-items: center;
    padding-bottom: 30px;
    transition: all 0.5s ease;
    border-radius: 18px;
    border: 2px solid transparent;
}

.premium-gallery-item:hover .premium-item-overlay {
    opacity: 1;
    border-color: rgba(210, 55, 121, 0.4);
}

.premium-item-overlay i {
    font-size: 40px;
    color: #d23779;
    transform: translateY(30px) scale(0.8);
    transition: all 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.275);
    text-shadow: 0 4px 10px rgba(0,0,0,0.1);
}

.premium-gallery-item:hover .premium-item-overlay i {
    transform: translateY(0) scale(1);
    color: #d23779;
}

/* Lightbox Modal Override for premium feel */
#baguetteBox-overlay {
    background: rgba(255, 255, 255, 0.95) !important;
    backdrop-filter: blur(12px);
}

#baguetteBox-overlay .full-image img {
    border-radius: 12px;
    box-shadow: 0 10px 40px rgba(0,0,0,0.15), 0 0 50px rgba(210,55,121,0.2);
    border: 1px solid rgba(0,0,0,0.1);
}
#baguetteBox-overlay .baguetteBox-button {
    color: #333 !important;
    background-color: rgba(255,255,255,0.7) !important;
}
#baguetteBox-overlay .baguetteBox-button svg {
    fill: #333 !important;
}
</style>

<div class="premium-gallery-wrapper">
    <div class="premium-gallery-header">
        <h3><i class="fa fa-diamond" style="font-size: 24px; vertical-align: middle; margin-right: 10px;"></i> Exclusive Gallery</h3>
        <p>Premium High-Resolution Photos</p>
    </div>
    
    <div class="premium-gallery-grid tz-gallery">
        <c:forEach var="img" items="${imageObject}" begin="0" end="4" varStatus="theCount">
            <a class="lightbox premium-gallery-item" href="${pageContext.request.contextPath}/resources/UserImages/${img}">
                <img src="${pageContext.request.contextPath}/resources/UserImages/${img}" alt="Premium Photo" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                <div class="premium-item-overlay">
                    <i class="fa fa-expand"></i>
                </div>
            </a>
        </c:forEach>
    </div>
</div>

<script>
    function initGallery() {
        baguetteBox.run('.tz-gallery', {
            animation: 'fadeIn',
            noScrollbars: true,
            captions: false
        });
    }

    if (typeof baguetteBox !== 'undefined') {
        initGallery();
    } else {
        $("<link/>", {
           rel: "stylesheet",
           type: "text/css",
           href: "https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.css"
        }).appendTo("head");
        
        $.getScript("https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.js", function() {
            initGallery();
        });
    }
</script>