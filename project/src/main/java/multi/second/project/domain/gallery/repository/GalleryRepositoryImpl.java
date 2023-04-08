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
    public List<Gallery> testQueryDSL(String title, boolean isDel) {

        List<Gallery> gallerys = query.select(gallery)
                .from(gallery)
                .where(gallery.title.contains(title).and(gallery.isDel.eq(isDel)))
                .fetch();

        return gallerys;
    }

}