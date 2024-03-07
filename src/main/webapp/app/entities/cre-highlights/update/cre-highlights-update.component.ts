import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IIndividualAssessment } from 'app/entities/individual-assessment/individual-assessment.model';
import { IndividualAssessmentService } from 'app/entities/individual-assessment/service/individual-assessment.service';
import { ICREHighlights } from '../cre-highlights.model';
import { CREHighlightsService } from '../service/cre-highlights.service';
import { CREHighlightsFormService, CREHighlightsFormGroup } from './cre-highlights-form.service';

@Component({
  standalone: true,
  selector: 'jhi-cre-highlights-update',
  templateUrl: './cre-highlights-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CREHighlightsUpdateComponent implements OnInit {
  isSaving = false;
  cREHighlights: ICREHighlights | null = null;

  individualAssessmentsSharedCollection: IIndividualAssessment[] = [];

  editForm: CREHighlightsFormGroup = this.cREHighlightsFormService.createCREHighlightsFormGroup();

  constructor(
    protected cREHighlightsService: CREHighlightsService,
    protected cREHighlightsFormService: CREHighlightsFormService,
    protected individualAssessmentService: IndividualAssessmentService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareIndividualAssessment = (o1: IIndividualAssessment | null, o2: IIndividualAssessment | null): boolean =>
    this.individualAssessmentService.compareIndividualAssessment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cREHighlights }) => {
      this.cREHighlights = cREHighlights;
      if (cREHighlights) {
        this.updateForm(cREHighlights);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cREHighlights = this.cREHighlightsFormService.getCREHighlights(this.editForm);
    if (cREHighlights.id !== null) {
      this.subscribeToSaveResponse(this.cREHighlightsService.update(cREHighlights));
    } else {
      this.subscribeToSaveResponse(this.cREHighlightsService.create(cREHighlights));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICREHighlights>>): void {
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

  protected updateForm(cREHighlights: ICREHighlights): void {
    this.cREHighlights = cREHighlights;
    this.cREHighlightsFormService.resetForm(this.editForm, cREHighlights);

    this.individualAssessmentsSharedCollection =
      this.individualAssessmentService.addIndividualAssessmentToCollectionIfMissing<IIndividualAssessment>(
        this.individualAssessmentsSharedCollection,
        cREHighlights.individualassessment,
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
            this.cREHighlights?.individualassessment,
          ),
        ),
      )
      .subscribe((individualAssessments: IIndividualAssessment[]) => (this.individualAssessmentsSharedCollection = individualAssessments));
  }
}
