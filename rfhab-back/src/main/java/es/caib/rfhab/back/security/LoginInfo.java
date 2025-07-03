package es.caib.rfhab.back.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.caib.rfhab.model.entity.Entitat;
import es.caib.rfhab.persistence.EntitatJPA;
import es.caib.rfhab.persistence.UsuariEntitatJPA;
import es.caib.rfhab.persistence.UsuariJPA;

/**
 * Informació disponible durant el cicle de vida de l'aplicació en la Sessio
 * HTTP. Veure BasePreparer
 * 
 * @author anadal
 * 
 */
public class LoginInfo {
	
	protected final Logger log = Logger.getLogger(getClass());

    final User springSecurityUser;

    Set<String> roles;

    Set<GrantedAuthority> grantedAuthorities;

    final String username;
    
    UsuariJPA usuariPersona;
    
    Long entitatIDActual;
    
    String codiDir3Actual;
    
    boolean needConfigUser;
    
    Map<Long, EntitatJPA> entitats = new HashMap<Long, EntitatJPA>();
    
    String rolActual; 
    
    
    public LoginInfo(User springSecurityUser, Set<GrantedAuthority> grantedAuthorities,
			String username, UsuariJPA usuariPersona, Long entitatIDActual, String codiDir3Actual, boolean needConfigUser) {
		super();
		this.springSecurityUser = springSecurityUser;
		this.grantedAuthorities = grantedAuthorities;
		this.username = username;
		this.usuariPersona = usuariPersona;
		this.entitatIDActual = entitatIDActual;
		this.codiDir3Actual = codiDir3Actual;
		this.needConfigUser = needConfigUser;
		
		this.roles = new HashSet<String>();

		for (GrantedAuthority grantedAuthority : this.grantedAuthorities) {
			this.roles.add(grantedAuthority.getAuthority());
		}
	}

	public Set<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public UsernamePasswordAuthenticationToken generateToken() {
        UsernamePasswordAuthenticationToken authToken;
        Set<GrantedAuthority> roles = getGrantedAuthorities();
        authToken = new UsernamePasswordAuthenticationToken(this.springSecurityUser, "", roles);
        authToken.setDetails(this);
        
        log.info(" ---------- generateToken -------------- ");
        log.info(" authToken: " + authToken);
        
        // imprimir la informacion de roles
        String rolesStr = "";
		if (roles != null) {
			for (GrantedAuthority role : roles) {
				rolesStr += role.getAuthority() + " ";
			}
		}
        
        log.info("roles => " + rolesStr);
        log.info("-----------------------------------------");
        
        return authToken;
    }

    public static LoginInfo getInstance() throws LoginException {
        Object obj;
        try {
            obj = SecurityContextHolder.getContext().getAuthentication().getDetails();
        } catch (Exception e) {
            // No TRADUIR !!!!
            throw new LoginException("Error intentant obtenir informació de Login.", e);
        }

        if (obj == null) {
            // No TRADUIR !!!!
            throw new LoginException("La informació de Login és buida");
        }

		Logger instanceLog = Logger.getLogger(LoginInfo.class);
		instanceLog.debug("obj => " + obj);
        if (obj instanceof LoginInfo) {
            return (LoginInfo) obj;
        } else {
            // No TRADUIR !!!!
            throw new LoginException("La informació de Login no és del tipus esperat." + " Hauria de ser de tipus "
                    + LoginInfo.class.getName() + " i és del tipus " + obj.getClass().getName());
        }
    }
    
    public UsuariJPA getUsuariPersona() {
        return usuariPersona;
    }

    public void setUsuariPersona(UsuariJPA usr) {
        this.usuariPersona = usr;
    }

    public Long getEntitatID() {
        return entitatIDActual;
    }

    public Set<String> getRoles() {
		return this.roles;
	}
    
    public static boolean hasRole(String role) {
        try {
            return LoginInfo.getInstance().getRoles().contains(role);
        } catch (Throwable th) {
            return false;
        }
    }

    public boolean isNeedConfigUser() {
        return needConfigUser;
    }

    public boolean getNeedConfigUser() {
        return needConfigUser;
    }

    public void setNeedConfigUser(boolean needConfigUser) {
        this.needConfigUser = needConfigUser;
    }

    public String getLanguage() {
        return getUsuariPersona().getIdiomaID();
    }

    public String getUsername() {
        return username;
    }
	public String getCodiDir3Actual() {
		return codiDir3Actual;
	}

	public void setCodiDir3Actual(String codiDir3Actual) {
		this.codiDir3Actual = codiDir3Actual;
	}

	public Long getEntitatIDActual() {
		return entitatIDActual;
	}

	public void setEntitatIDActual(Long entitatIDActual) {
		this.entitatIDActual = entitatIDActual;
	}

	public Map<Long,EntitatJPA> getEntitats() {
		return entitats;
	}

	public void setEntitats(Map<Long,EntitatJPA> entitats) {
		this.entitats = entitats;
	}

	public String getRolActual() {
		return rolActual;
	}

	public void setRolActual(String rolActual) {
		this.rolActual = rolActual;
	}

	@Override
	public String toString() {
		
		// imprimir la información de springSecurityUser
		String springSecurityUserStr = "";
		if (springSecurityUser != null) {
			springSecurityUserStr = springSecurityUser.toString();
		}
		
		// imprimir los roles
		String roles = "";
		if (this.roles != null) {
			for (String role : this.roles) {
				roles += role + " ";
			}
		}
		
		// imprimir las grantedAuthorities
		String grantedAuthorities = "";
		if (this.grantedAuthorities != null) {
			for (GrantedAuthority grantedAuthority : this.grantedAuthorities) {
				grantedAuthorities += grantedAuthority.getAuthority() + " ";
			}
		}
		
		// imprimir las entidades
		String entitats = "";
		if (this.entitats != null) {
			for (Map.Entry<Long, EntitatJPA> entitat : this.entitats.entrySet()) {
				entitats += entitat.getKey() + " => " + entitat.getValue() + " ";
			}
		}
		
		// imprimir la información de usuariPersona
		String usuariPersona = "";
		if (this.usuariPersona != null) {
			usuariPersona = this.usuariPersona.getUsername();
		}
		
		return "LoginInfo [springSecurityUser=" + springSecurityUserStr + ", roles=" + roles + ", grantedAuthorities="
				+ grantedAuthorities + ", username=" + username + ", usuariPersona=" + usuariPersona + ", entitats=" + entitats
				+ ", entitatIDActual=" + entitatIDActual + ", codiDir3Actual=" + codiDir3Actual  +  ", needConfigUser=" + needConfigUser + "]";
	} 
    

    
    
}
