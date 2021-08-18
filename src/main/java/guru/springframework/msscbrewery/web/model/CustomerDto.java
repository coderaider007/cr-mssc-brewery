package guru.springframework.msscbrewery.web.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author yvesc
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

	private UUID id;
	private String name;
}
