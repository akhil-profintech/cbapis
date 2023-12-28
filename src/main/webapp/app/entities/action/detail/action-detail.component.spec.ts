import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ActionDetailComponent } from './action-detail.component';

describe('Action Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActionDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ActionDetailComponent,
              resolve: { action: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ActionDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load action on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ActionDetailComponent);

      // THEN
      expect(instance.action).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
