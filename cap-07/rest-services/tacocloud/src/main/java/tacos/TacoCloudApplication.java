/**
 * 
 */
package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author clabrada
 *
 */

/**
 * https://stackoverflow.com/questions/62355039/spring-boot-scanbasepackages-unable-to-find-beans-from-dependency
 * Se utiliza para cuando el paquete del servicio principal no utiliza el mismo paquete
 *	@SpringBootApplication(scanBasePackages = {"tacos.web"})
 *	@EnableJpaRepositories(basePackages = "tacos.data")
 *	@EntityScan(basePackages = "tacos")
*/
@SpringBootApplication()
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}
}
