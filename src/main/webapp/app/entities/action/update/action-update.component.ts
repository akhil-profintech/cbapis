import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAction } from '../action.model';
import { ActionService } from '../service/action.service';
import { ActionFormService, ActionFormGroup } from './action-form.service';

@Component({
  standalone: true,
  selector: 'jhi-action-update',
  templateUrl: './action-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ActionUpdateComponent implements OnInit {
  isSaving = false;
  action: IAction | null = null;

  editForm: ActionFormGroup = this.actionFormService.createActionFormGroup();

  constructor(
    protected actionService: ActionService,
    protected actionFormService: ActionFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ action }) => {
      this.action = action;
      if (action) {
        this.updateForm(action);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const action = this.actionFormService.getAction(this.editForm);
    if (action.id !== null) {
      this.subscribeToSaveResponse(this.actionService.update(action));
    } else {
      this.subscribeToSaveResponse(this.actionService.create(action));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAction>>): void {
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

  protected updateForm(action: IAction): void {
    this.action = action;
    this.actionFormService.resetForm(this.editForm, action);
  }
}
