# TEST JPA

### Câu 1:
* Thuộc tính name trong @Entity:

Nếu để mặc định, tên bảng database sẽ là tên lớp của entity.

Ví dụ:

```
@Entity

public class HelloWorld{ }
```

=> Tên của entity trong database đều sẽ thay đổi là HelloWorld

Có thể điều chỉnh lại tên bảng bằng cách khai báo thêm thuộc tính name trong @Entity. Trong trường hợp này, tên của entity cũng sẽ thay đổi theo.

Ví dụ:

```
@Entity(name = "Hello")

public class HelloWorld{ }
```

=> Tên của class và tên của entity trong database đều sẽ thay đổi là Hello

* Thuộc tính name trong @Table

Trong trường hợp, chúng ta chỉ muốn thay đổi tên bảng của database mà không thay đổi tên của entity, sử dụng @Table annotation sẽ đáp ứng nhu cầu đó

Ví dụ:

```
@Entity

@Table(name = "Hello")

public class HelloWorld{ }
```

=> Tên bảng mà chúng ta sẽ sử dụng là Hello còn tên entity vẫn là HelloWorld

### Câu 2:
Để debug câu lệnh SQL mà Hibernate sẽ sinh ra trong quá trình thực thi, cần phải bổ sung lệnh 
"spring.jpa.show-sql=true"
vào file application.properties

### Câu 3:
* Tham số "name" trong @Column sẽ đổi lại tên cột nếu muốn khác với tên thuộc tính
* Tham số "unique" chỉ định yêu cầu duy nhất, không được trùng lặp dữ liệu
* Tham số "nullable" buộc trường không được null

### Câu 4:
* @PrePersist: Ngay trước khi đối tượng Entity lưu xuống CSDL
* @PreUpdate: Ngay trước khi đối tượng Entity cập nhật xuống CSDL

### Câu 5: 
JpaRepository kế thừa từ interface PagingAndSortingRepository

### Câu 6:
public interface PostRepository extends JpaRepository<Post, Long> { }

### Câu 7:
Khi đã chọn một cột là Identity dùng @Id để đánh dấu, thì không cần phải dùng xác định unique dùng annotation @Column(unique=true)

### Câu 8:
* Tìm tất cả các Employee theo emailAddress và lastName:

List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);


* Tìm tất cả các Employee khác nhau theo firstName hoặc lastName:

Set<Employee> findByFirstNameOrLastName(String firstName, String lastName);


* Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần:

List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);

* Tìm tất cả các Employee theo fistName không phân biệt hoa thường:

List<Employee> findByFirstNameIgnoreCase(String firstName);

### Câu 9:
* Với annotation @Query ta có thể khai báo câu query cho các method trong repository.

Ví dụ:

    @Query("select e from Employee e where e.emailAddress=?1")
    List<Employee> findAllByEmailAddress(String emailAddress);

* Trong Class Entity, mình sử dụng @NameQuery để tạo câu lệnh Select. 

```
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Employee.fetchByFirstName",
query = "SELECT e FROM Employee e WHERE e.lastName like : firstName"
)
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "email_address", unique = true)
    private String emailAddress;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
}
```


Để gọi được câu lệnh @NamedQuery(name = “Employee.fetchByLastNameLength”)
thì ở JPA Repository, ta phải có phương thức (fetchByLastNameLength) giống y như vậy.

    List<Employee> fetchByFirstName(String firstName);

### Câu 10:
* Tạo phương thức ở EmployeeRepository

```
    List<Employee> findByFirstNameContains(String firstName, Sort sort);
    Page<Employee> findByLastNameContains(String lastName, Pageable pageable);
```

* Sử dụng sorting và paging khi query đối tượng Employee ở trên
```
    @Test
    void sortByFirstName() {
        List<Employee> employees = employeeRepository.findByFirstNameContains("a", Sort.by("firstName"));
        employees.forEach(System.out::println);
    }

    @Test
    void findByLastNameContains() {
        Page<Employee> pages = employeeRepository.findByLastNameContains("er", PageRequest.of(1,10));
        pages.getContent().forEach(System.out::println);
        System.out.println(pages.getTotalPages());
        System.out.println(pages.getTotalElements());
    }
```

### Câu 11:
* Entity Product:

```
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private Set<Tag> tags = new LinkedHashSet<>();

    @PreRemove
    public void preRemove() {
        this.setCategory(null);
    }
}
```

* Entity Tag

```
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tag tag = (Tag) o;
        return id != null && Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
```

### Câu 12:



