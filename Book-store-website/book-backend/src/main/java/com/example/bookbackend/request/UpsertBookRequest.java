package com.example.bookbackend.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertBookRequest {
    private String title;
    private String description;
//    private String thumbnail;
    private Integer pageNumbers;
    private Integer publishingYear;
    private Long price;
    private String thumbnail;
    private List<Integer> authorIds;//danh sach id cua author
    private List<Integer> categoryIds; // danh sách id của category
    private Integer quantity;
}
