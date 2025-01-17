import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IIndividualAssessment } from 'app/entities/individual-assessment/individual-assessment.model';
import { IndividualAssessmentService } from 'app/entities/individual-assessment/service/individual-assessment.service';
import { ICREObservations } from '../cre-observations.model';
import { CREObservationsService } from '../service/cre-observations.service';
import { CREObservationsFormService, CREObservationsFormGroup } from './cre-observations-form.service';

@Component({
  standalone: true,
  selector: 'jhi-cre-observations-update',
  templateUrl: './cre-observations-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CREObservationsUpdateComponent implements OnInit {
  isSaving = false;
  cREObservations: ICREObservations | null = null;

  individualAssessmentsSharedCollection: IIndividualAssessment[] = [];

  editForm: CREObservationsFormGroup = this.cREObservationsFormService.createCREObservationsFormGroup();

  constructor(
    protected cREObservationsService: CREObservationsService,
    protected cREObservationsFormService: CREObservationsFormService,
    protected individualAssessmentService: IndividualAssessmentService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareIndividualAssessment = (o1: IIndividualAssessment | null, o2: IIndividualAssessment | null): boolean =>
    this.individualAssessmentService.compareIndividualAssessment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cREObservations }) => {
      this.cREObservations = cREObservations;
      if (cREObservations) {
        this.updateForm(cREObservations);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cREObservations = this.cREObservationsFormService.getCREObservations(this.editForm);
    if (cREObservations.id !== null) {
      this.subscribeToSaveResponse(this.cREObservationsService.update(cREObservations));
    } else {
      this.subscribeToSaveResponse(this.cREObservationsService.create(cREObservations));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICREObservations>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(cREObservations: ICREObservations): void {
    this.cREObservations = cREObservations;
    this.cREObservationsFormService.resetForm(this.editForm, cREObservations);

    this.individualAssessmentsSharedCollection =
      this.individualAssessmentService.addIndividualAssessmentToCollectionIfMissing<IIndividualAssessment>(
        this.individualAssessmentsSharedCollection,
        cREObservations.individualassessment,
      );
  }

  protected loadRelationshipsOptions(): void {
    this.individualAssessmentService
      .query()
      .pipe(map((res: HttpResponse<IIndividualAssessment[]>) => res.body ?? []))
      .pipe(
        map((individualAssessments: IIndividualAssessment[]) =>
          this.individualAssessmentService.addIndividualAssessmentToCollectionIfMissing<IIndividualAssessment>(
            individualAssessments,
            this.cREObservations?.individualassessment,
          ),
        ),
      )
      .subscribe((individualAssessments: IIndividualAssessment[]) => (this.individualAssessmentsSharedCollection = individualAssessments));
  }
}
