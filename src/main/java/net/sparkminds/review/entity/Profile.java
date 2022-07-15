package net.sparkminds.review.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@Table(name = "profile")
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
public class Profile extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "name")
    private String name;

    @Column(name = "github_user")
    private String githubUser;

    @JsonIgnore
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<Project> projects;
}
