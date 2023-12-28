import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';
import { CBCREProcessService } from 'app/entities/cbcre-process/service/cbcre-process.service';
import { IIndividualAssessment } from '../individual-assessment.model';
import { IndividualAssessmentService } from '../service/individual-assessment.service';
import { IndividualAssessmentFormService, IndividualAssessmentFormGroup } from './individual-assessment-form.service';

@Component({
  standalone: true,
  selector: 'jhi-individual-assessment-update',
  templateUrl: './individual-assessment-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class IndividualAssessmentUpdateComponent implements OnInit {
  isSaving = false;
  individualAssessment: IIndividualAssessment | null = null;

  cBCREProcessesSharedCollection: ICBCREProcess[] = [];

  editForm: IndividualAssessmentFormGroup = this.individualAssessmentFormService.createIndividualAssessmentFormGroup();

  constructor(
    protected individualAssessmentService: IndividualAssessmentService,
    protected individualAssessmentFormService: IndividualAssessmentFormService,
    protected cBCREProcessService: CBCREProcessService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareCBCREProcess = (o1: ICBCREProcess | null, o2: ICBCREProcess | null): boolean =>
    this.cBCREProcessService.compareCBCREProcess(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ individualAssessment }) => {
      this.individualAssessment = individualAssessment;
      if (individualAssessment) {
        this.updateForm(individualAssessment);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const individualAssessment = this.individualAssessmentFormService.getIndividualAssessment(this.editForm);
    if (individualAssessment.id !== null) {
      this.subscribeToSaveResponse(this.individualAssessmentService.update(individualAssessment));
    } else {
      this.subscribeToSaveResponse(this.individualAssessmentService.create(individualAssessment));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIndividualAssessment>>): void {
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

  protected updateForm(individualAssessment: IIndividualAssessment): void {
    this.individualAssessment = individualAssessment;
    this.individualAssessmentFormService.resetForm(this.editForm, individualAssessment);

    this.cBCREProcessesSharedCollection = this.cBCREProcessService.addCBCREProcessToCollectionIfMissing<ICBCREProcess>(
      this.cBCREProcessesSharedCollection,
      individualAssessment.cbcreprocess,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.cBCREProcessService
      .query()
      .pipe(map((res: HttpResponse<ICBCREProcess[]>) => res.body ?? []))
      .pipe(
        map((cBCREProcesses: ICBCREProcess[]) =>
          this.cBCREProcessService.addCBCREProcessToCollectionIfMissing<ICBCREProcess>(
            cBCREProcesses,
            this.individualAssessment?.cbcreprocess,
          ),
        ),
      )
      .subscribe((cBCREProcesses: ICBCREProcess[]) => (this.cBCREProcessesSharedCollection = cBCREProcesses));
  }
}
