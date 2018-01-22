package introsde.document.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Primary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="\"userId\"")
	private String userId;
    
    @Column(name="\"artistId\"")
    private String artistId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Primary taskId1 = (Primary) o;
        if (userId != taskId1.userId) return false;
        return artistId == taskId1.artistId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, artistId);
    }
    
}