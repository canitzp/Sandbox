package org.sandboxpowered.sandbox.fabric.security;

import org.sandboxpowered.sandbox.fabric.impl.AddonSpec;

import java.io.FilePermission;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.SecureClassLoader;

public class AddonClassLoader extends SecureClassLoader {
    static {
        registerAsParallelCapable();
    }

    private final AddonSpec spec;
    private DynamicURLClassLoader urlClassLoader = (DynamicURLClassLoader) getParent();

    public AddonClassLoader(ClassLoader original, AddonSpec spec) {
        super(new DynamicURLClassLoader(new URL[0], original));
        this.spec = spec;
    }

    public void addURL(URL url) {
        urlClassLoader.addURL(url);
    }

    @Override
    protected PermissionCollection getPermissions(CodeSource codesource) {
        Permissions pc = new Permissions();
        pc.add(new FilePermission("data/-", "read")); // Can read everything from data dir
        pc.add(new FilePermission(String.format("data/%s/-", spec.getId()), "read,write,delete")); // Can write everything inside addon data dir
        return pc;
    }

    private static class DynamicURLClassLoader extends URLClassLoader {
        static {
            registerAsParallelCapable();
        }

        private DynamicURLClassLoader(URL[] urls, ClassLoader original) {
            super(urls, original);
        }

        public void addURL(URL url) {
            super.addURL(url);
        }
    }
}