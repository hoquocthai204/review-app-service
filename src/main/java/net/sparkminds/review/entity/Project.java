package net.sparkminds.review.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.sparkminds.review.entity.enumeration.CapacityStatus;
import net.sparkminds.review.entity.enumeration.EmploymentMode;

@Entity
@Table(name = "t_project")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Project extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_mode", nullable = false)
    private EmploymentMode employmentMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "capacity", nullable = false)
    private CapacityStatus capacity;

    @Column(name = "duration_in_months")
    private String durationInMonths;

    @Column(name = "start_year", nullable = false)
    private String startYear;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "team_size", nullable = false)
    private Integer teamSize;

    @Column(name = "link_repo")
    private String linkRepo;

    @Column(name = "link_live")
    private String linkLive;

    @Column(name = "is_deleted")
    private Boolean delete = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_profile_id")
    private Profile profile;
}
