package com.eclectics.io.user_auth_service.dto;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(@NotNull String username, @NotNull String password) {}