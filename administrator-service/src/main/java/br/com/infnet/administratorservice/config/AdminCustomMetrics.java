package br.com.infnet.administratorservice.config;

import br.com.infnet.administratorservice.service.AdminAlunoService;
import br.com.infnet.administratorservice.service.AdminProfessorService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AdminCustomMetrics implements MeterBinder {
    @Autowired
    private AdminAlunoService adminAlunoService;
    @Autowired
    private AdminProfessorService adminProfessorService;

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        Gauge.builder("Alunos Registrados", this, value ->
                    adminAlunoService.getAllAluno().stream().count())
                .description("Quantidade de alunos registrados")
                .tags(Tags.of(Tag.of("data", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .register(meterRegistry);
        Gauge.builder("Professores Registrados", this, value ->
                    adminProfessorService.getAllProfessor().stream().count())
                .description("Quantidade de professsores registrados")
                .tags(Tags.of(Tag.of("data", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .register(meterRegistry);
    }
}
