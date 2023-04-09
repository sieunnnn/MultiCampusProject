package multi.second.project.domain.gallery.repository;

import java.util.List;

import multi.second.project.domain.gallery.domain.Gallery;


public interface GalleryRepositoryExtension {

    List<Gallery> testQueryDSL(String title, boolean isDel);

}