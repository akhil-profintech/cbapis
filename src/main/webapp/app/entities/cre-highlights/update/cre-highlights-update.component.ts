import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';
import { CBCREProcessService } from 'app/entities/cbcre-process/service/cbcre-process.service';
import { IIndividualAssessment } from 'app/entities/individual-assessment/individual-assessment.model';
import { IndividualAssessmentService } from 'app/entities/individual-assessment/service/individual-assessment.service';
import { CREHighlightsService } from '../service/cre-highlights.service';
import { ICREHighlights } from '../cre-highlights.model';
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

  cBCREProcessesSharedCollection: ICBCREProcess[] = [];
  individualAssessmentsSharedCollection: IIndividualAssessment[] = [];

  editForm: CREHighlightsFormGroup = this.cREHighlightsFormService.createCREHighlightsFormGroup();

  constructor(
    protected cREHighlightsService: CREHighlightsService,
    protected cREHighlightsFormService: CREHighlightsFormService,
    protected cBCREProcessService: CBCREProcessService,
    protected individualAssessmentService: IndividualAssessmentService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareCBCREProcess = (o1: ICBCREProcess | null, o2: ICBCREProcess | null): boolean =>
    this.cBCREProcessService.compareCBCREProcess(o1, o2);

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

    this.cBCREProcessesSharedCollection = this.cBCREProcessService.addCBCREProcessToCollectionIfMissing<ICBCREProcess>(
      this.cBCREProcessesSharedCollection,
      cREHighlights.cbcreprocess,
    );
    this.individualAssessmentsSharedCollection =
      this.individualAssessmentService.addIndividualAssessmentToCollectionIfMissing<IIndividualAssessment>(
        this.individualAssessmentsSharedCollection,
        cREHighlights.individualassessment,
      );
  }

  protected loadRelationshipsOptions(): void {
    this.cBCREProcessService
      .query()
      .pipe(map((res: HttpResponse<ICBCREProcess[]>) => res.body ?? []))
      .pipe(
        map((cBCREProcesses: ICBCREProcess[]) =>
          this.cBCREProcessService.addCBCREProcessToCollectionIfMissing<ICBCREProcess>(cBCREProcesses, this.cREHighlights?.cbcreprocess),
        ),
      )
      .subscribe((cBCREProcesses: ICBCREProcess[]) => (this.cBCREProcessesSharedCollection = cBCREProcesses));

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
