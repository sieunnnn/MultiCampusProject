package multi.second.project.domain.gallery.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.gallery.domain.QGallery;

public class GalleryRepositoryImpl implements GalleryRepositoryExtension{

	private final JPAQueryFactory query;
	private QGallery gallery = QGallery.gallery;
	
	public GalleryRepositoryImpl(JPAQueryFactory query) {
		this.query = query;
	}
	
	@Override
	public List<Gallery> testMethod(String title, boolean isDel) {
		
		List<Gallery> gallerys = query.select(gallery)
				.from(gallery)
				.where(gallery.title.contains(title).and(gallery.isDel.eq(isDel)))
				.fetch();
		
		return gallerys;

	}

	// 쿼리 메소드는 스프링 데이터 JPA에서 자동으로 지원해주는 것이고,
	// 개발자가 직접 작성하는 쿼리 DSL은 QueryDSL이용해 작성하게 됩니다.

	//이 코드는 QueryDSL을 이용하여 title과 isDel 값을 기준으로 Gallery 엔티티를 조회하는 코드입니다.
	//JPAQueryFactory: QueryDSL을 사용하기 위한 클래스로, EntityManager를 인자로 받아 생성됩니다.
	//QGallery: QueryDSL에서 사용하는 메타 모델 클래스로, Gallery 엔티티의 필드와 관계를 정의한 클래스입니다.
	// QGallery.gallery와 같이 필드명.gallery 형식으로 사용됩니다.
	//testQueryDSL 메소드: QueryDSL을 사용하여 title과 isDel 값을 기준으로 Gallery 엔티티를 조회하는 메소드입니다.
	// query.select(gallery)에서는 QGallery 클래스에서 정의한 필드와 관계를 사용하여 쿼리를 생성합니다.
	// where절에서는 and()와 contains() 메소드를 사용하여 title 값이 포함된 게시글 중 isDel이 false인 게시글을 조회합니다.
	// fetch() 메소드를 호출하여 쿼리를 실행하고, 조회한 결과를 List<Gallery> 형태로 반환합니다.
	//이 코드에서는 QueryDSL을 이용하여 JPA 엔티티를 조회하는 방법을 보여주고 있습니다.




	//QGallery 객체는 QueryDSL에서 사용되는 엔티티 클래스의 메타 모델 클래스입니다.
	//
	//QueryDSL은 JPQL을 더욱 쉽고 간결하게 작성할 수 있게 도와주는 프레임워크입니다.
	// 하지만, QueryDSL을 사용하기 위해서는 JPQL과는 다른 문법을 사용해야 합니다.
	// 이때, QueryDSL에서는 엔티티 클래스의 메타 모델 클래스를 사용하여 쿼리를 작성합니다.
	//
	//메타 모델 클래스란, 엔티티 클래스의 정보를 담고 있는 클래스로, 엔티티 클래스의 각 필드와 관계, 메소드 등을 표현합니다.
	// Q클래스라고도 불리며, JPA에서 메타 모델 클래스를 자동으로 생성해주는 기능을 제공합니다.
	//
	//QGallery는 Gallery 엔티티 클래스에 대한 메타 모델 클래스로, 해당 엔티티 클래스의 정보를 담고 있습니다.
	// 이를 이용하여 QueryDSL에서 Gallery 엔티티에 대한 쿼리를 작성할 수 있습니다.
}
