/**
 * 
 */
package tacos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author clabrada
 *
 */

@Component
@ConfigurationProperties(prefix="taco.discount")
@Data
public class DiscountCodeProps {

}
