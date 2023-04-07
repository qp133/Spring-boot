package com.example.bookbackend;

import com.example.bookbackend.entity.*;
import com.example.bookbackend.exception.NotFoundException;
import com.example.bookbackend.repository.*;
import com.example.bookbackend.service.FileService;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.*;

@SpringBootTest
public class InitDataTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private Faker faker;

    @Autowired
    private Slugify slugify;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private  String[] images={"https://product.hstatic.net/1000363117/product/tb30-1_7a670519dc344827af0cc77ba6c15afa_master.jpg",
            "https://product.hstatic.net/1000363117/product/8936071672704_85103de7755547f7995cbf7098e788d5_master.jpg",
            "https://product.hstatic.net/1000363117/product/viet_nam_su_luoc_1_e857764da05944968090120af109fdc8_large_2bf522dc7ff74587be34231c5504ac97_master.jpg",
            "https://product.hstatic.net/1000363117/product/8934974184003-dd_9f91a1c753d94b43a97fbc49cb05d6c4_master.jpg",
            "https://product.hstatic.net/1000363117/product/image_195509_1_16506_72d7d292f109433693249f4a91f9ee35_master.jpg",
            "https://product.hstatic.net/1000363117/product/image_195509_1_28893_a116a4bf5b4a47148908908c24d7df51_master.jpg",
            "https://salt.tikicdn.com/cache/280x280/ts/product/8d/55/83/657a633328880cc2956fb7d781cd792d.jpg.webp",
            "https://salt.tikicdn.com/cache/280x280/ts/product/90/49/97/ec88ab408c1997378344486c94dbac40.jpg.webp",
            "https://salt.tikicdn.com/cache/280x280/ts/product/8d/f8/fa/4157d263510f10ac7488fb99640d7587.jpg.webp",
            "https://salt.tikicdn.com/cache/280x280/ts/product/6b/98/82/8d6ef80eb54c5fb1e2d8f6e74de9ca6c.jpg.webp",
            "https://salt.tikicdn.com/cache/280x280/ts/product/03/03/07/3fbe7cc39c9fe08b308274a03099c1c1.jpg.webp",
            "https://salt.tikicdn.com/cache/280x280/ts/product/0c/03/69/08018013f5bebd21b575b68ef45a2b73.jpg.webp",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/955/9788175992955.jpg",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/894/9788172344894.jpg",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/795/9788172344795.jpg",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/136/9788172345136.jpg",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/344/9781501110344.jpg",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/127/9780241379127.jpg",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/579/9780712670579.jpg",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/720/9781786892720.jpg",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/266/9789387779266.jpg",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/194/9781612680194.jpg",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/294/9789391924294.jpg",
            "https://d2g9wbak88g7ch.cloudfront.net/productimages/mainimages/357/9789354600357.jpg",
            "https://product.hstatic.net/1000363117/product/image_195509_1_30207_d049ec32586948cc85a84cfb5dcd1df8_master.jpg",
            "https://product.hstatic.net/1000363117/product/image_89545_148dc1a847e74ab2b1894a71c424ded8_master.jpg",
            "https://product.hstatic.net/1000363117/product/image_180747_ac752a060ff74a4cbc58a02593ee5ffc_master.jpg",
            "https://product.hstatic.net/1000363117/product/7_hanh_trinh_tri_tue_80a251dc072c474a93707c206d2a8c93_master.png",
            "https://product.hstatic.net/1000363117/product/7_hanh_trinh_tri_tue_80a251dc072c474a93707c206d2a8c93_master.png",
            "https://product.hstatic.net/1000363117/product/2020_04_07_14_50_16_1-390x510_07e901bf3b994e1c9965023df727eb0f_master.jpg",
            "https://product.hstatic.net/1000363117/product/ly_do_hanh_phuc_2a4f70449c1844a181929806e6e79163_master.png",
            "https://product.hstatic.net/1000363117/product/tuong_tac_lat_mo_-_cac_tu_trai_nghia_-_opposites_9ab4ce6196b046ad844d12a72c3a4252_master.jpg",
            "https://product.hstatic.net/1000363117/product/image_187319_a566274994de4dd68fdb2b3e4ed42688_master.jpg",
            "https://product.hstatic.net/1000363117/product/se-co-cach-dung-lo_1_668d017544bd447d87610a2075fc3f9e_master.jpg",
            "https://product.hstatic.net/1000363117/product/do_be_o_sau_la_gi__trang_phuc_3ef902810911474988b9d9cdc19f70e0_master.png"
    };
    private  String[] avatar={"https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-4.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-3.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-2.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-5.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-6.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-7.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-8.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-9.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-10.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-11.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-12.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-13.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-14.jpg",
            "https://thao68.com/wp-content/uploads/2022/03/avatar-facebook-15.jpg"
    };

    @Test
    void save_user() {
        Random rd=new Random();
        User user1 = User.builder()
                .name(faker.name().fullName())
                .email("admin")
                .password(passwordEncoder.encode("111"))
                .avatar(avatar[rd.nextInt(avatar.length)])
                .roles(List.of("USER", "ADMIN"))
                .build();
        userRepository.save(user1);
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .avatar(avatar[rd.nextInt(avatar.length)])
                    .password(passwordEncoder.encode("111"))
                    .roles(List.of("USER", "ADMIN"))
                    .build();
            userRepository.save(user);
        }
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .avatar(avatar[rd.nextInt(avatar.length)])
                    .password(passwordEncoder.encode("111"))
                    .roles(List.of("USER"))
                    .build();
            userRepository.save(user);
        }
    }

    @Test
    void save_category() {

        String[] categories={"Tình cảm","Trinh thám","Thiếu nhi",
        "Kinh doanh","Tâm lý","Tiên Hiệp","Lịch sử-quân sự"};
        for (int i = 0; i < 7; i++) {
            Category category = Category.builder()
                    .name(categories[i])
                    .build();
            categoryRepository.save(category);
        }
    }

    @Test
    void save_author() {
        Author author1 = Author.builder()
                .name("A Quang")
                .build();
        authorRepository.save(author1);
        for (int i = 0; i < 10; i++) {
            Author author = Author.builder()
                    .name(faker.name().fullName())
                    .build();
            authorRepository.save(author);
        }
    }

    @Test
    void save_book() {
        Random rd=new Random();
        List<User> users = userRepository.findAll();
        List<User> usersHasRoleAdmin = users.stream()
                .filter(user -> user.getRoles().contains("ADMIN"))
                .toList();

        List<Category> categories = categoryRepository.findAll();

        List<Author> authors=authorRepository.findAll();

        for (int i = 0; i < 120; i++) {
            // Random 1 user
            User rdUser = usersHasRoleAdmin.get(rd.nextInt(usersHasRoleAdmin.size()));

            // Random 1 ds category
            Set<Category> rdListCategory = new HashSet<>();
            for (int j = 0; j < 1; j++) {
                Category rdCategory = categories.get(rd.nextInt(categories.size()));
                rdListCategory.add(rdCategory);
            }
            //Random author
            Set<Author> rdListAuthor = new HashSet<>();
            for (int j = 0; j < 1; j++) {
                Author rdAuthor = authors.get(rd.nextInt(authors.size()));
                rdListAuthor.add(rdAuthor);
            }

            // Tạo book
            String title = faker.book().title();
            Book book = Book.builder()
                    .title(title)
                    .slug(slugify.slugify(title))
                    .description(faker.lorem().sentence(30))
                    .categories(rdListCategory)
                    .authors(rdListAuthor)
                    .thumbnail(images[rd.nextInt(images.length)])
                    .price(rd.nextLong(100,1000)*1000)
                    .quantity(rd.nextInt(10)+1)
                    .pageNumbers(rd.nextInt(100,1000))
                    .publishingYear(rd.nextInt(2000,2022))
                    .build();

            bookRepository.save(book);
        }
    }

    @Test
    void save_comment() {
        Random rd = new Random();

        List<User> users = userRepository.findAll();
        List<Book> books = bookRepository.findAll();

        for (int i = 0; i < 600; i++) {
            // Random 1 user
            User rdUser = users.get(rd.nextInt(users.size()));

            // Random 1 book
            Book rdBook = books.get(rd.nextInt(books.size()));
            List<String> commentContent=List.of("Tôi rất thích quyển sách này, nội dung cực kỳ hấp dẫn và tạo cảm hứng",
                    "Cảm giác đọc sách này giống như tôi đang đắm mình trong một thế giới mới, tác giả viết rất hay",
                    "Tôi thấy nhân vật chính rất đáng yêu và mối tình của họ đã làm tôi nấp nặng trong suốt quá trình đọc.",
                    "Các tình tiết của sách đã làm tôi cảm thấy sốc và choáng váng.",
                    "Tôi rất thích cách tác giả kể câu chuyện, tôi cảm thấy như tôi đang xem một bộ phim",
                    "Tôi đã học được rất nhiều điều mới từ sách này và tôi sẽ giới thiệu nó cho bạn bè của tôi.",
                    "Tôi rất thích cách tác giả  sáng tạo và xây dựng cốt truyện hấp dẫn trong quyển sách này.",
                    "Nội dung trong sách rất gần gũi với cuộc sống hàng ngày và giúp tôi tìm hiểu thêm về mình và xung quanh.",
                    "Tác giả viết rất sâu sắc về những tình huống và cảm xúc của nhân vật, giúp tôi trải nghiệm sâu sắc hơn vào truyện.",
                    "Sách có nhiều điều mà tôi chưa biết và rất hữu ích cho sự phát triển của tôi.",
                    "Tôi khuyên mọi người nên đọc quyển sách này để cảm nhận những gì tác giả muốn truyền đạt.");


            Comment comment = Comment.builder()
                    .content(commentContent.get(rd.nextInt(commentContent.size())))
                    .user(rdUser)
                    .book(rdBook)
                    .build();
            commentRepository.save(comment);
        }
    }
    @Test
    void deleteComment(){
        commentRepository.deleteAll();
    }
}
