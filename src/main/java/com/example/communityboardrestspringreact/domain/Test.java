package com.example.communityboardrestspringreact.domain;

import com.example.communityboardrestspringreact.util.BooleanToStringConverter;
import com.example.communityboardrestspringreact.util.BooleanToYnConverter;
import lombok.*;

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

    @Convert(converter = BooleanToYnConverter.class)
    private Boolean useYn;

    @Convert(converter = BooleanToStringConverter.class)
    private Boolean useEnabled;

}
