package br.com.tnt.ssg.dmp;

import java.io.Serializable;

public interface BaseEntity extends Serializable {

	Long getId();

	void setId(Long id);
}
