package com.example.communityboardrestspringreact.domain;

import com.example.communityboardrestspringreact.util.BooleanToStringConverter;
import com.example.communityboardrestspringreact.util.BooleanToYnConverter;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Convert(converter = BooleanToYnConverter.class)
    @Type(type = "yes_no")
    private Boolean useYn;

    @Convert(converter = BooleanToStringConverter.class)
    private Boolean useEnabled;

}
