package MTZ.mountainz.domain.queryDSL;

import MTZ.mountainz.domain.certification.dto.request.PhotoFilterRequestDto;
import MTZ.mountainz.domain.certification.dto.response.CertificationFilterResponseDto;
import MTZ.mountainz.domain.certification.entity.Certification;
import MTZ.mountainz.domain.certification.repository.CertificationRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static MTZ.mountainz.domain.certification.entity.QCertification.*;



@Repository
@RequiredArgsConstructor
public class CertificationRepositoryImpl implements CertificationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<CertificationFilterResponseDto> findByPhotosFilter(PhotoFilterRequestDto photoFilterRequestDto) {

        List<CertificationFilterResponseDto> certifications = jpaQueryFactory.select(Projections.fields(CertificationFilterResponseDto.class, certification.photo, certification.member.nickName, certification.mountain.name, certification.mountain.region))
                .from(certification)
                .where(eqRegion(photoFilterRequestDto.getRegion()),
                        eqName(photoFilterRequestDto.getName())
                )
                .fetch();

        return certifications;
    }

    //findByPhotosFilter 메서드
    private BooleanExpression eqRegion(String region) {
        return region != null ? certification.mountain.region.eq(region) : null;
    }

    private BooleanExpression eqName(String name) {
        return name != null ? certification.mountain.name.eq(name) : null;
    }
}
