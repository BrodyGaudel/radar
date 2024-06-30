package org.mounanga.registrationservice.commands.util.owner;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerCinRepository extends JpaRepository<OwnerCin, String> {

    boolean existsByCin(String cin);

    @Override
    boolean existsById(@NotNull String s);

    OwnerCin findByCin(String cin);
}
