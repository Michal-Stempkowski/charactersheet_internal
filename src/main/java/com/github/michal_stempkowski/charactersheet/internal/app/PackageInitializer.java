package com.github.michal_stempkowski.charactersheet.internal.app;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Class responsible for initialization of all domains from single target-specific package.
 */
public abstract class PackageInitializer {
    public List<Domain> init() {
        List<Domain> domains = getDomainCreators().stream()
                .map(Supplier::get)
                .collect(Collectors.toList());

        domains.forEach(Domain::setup);

        return domains;
    }

    protected abstract List<Supplier<Domain>> getDomainCreators();
}
