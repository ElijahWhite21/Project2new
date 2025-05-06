function retval = PSS2_Smoothing(input1, input2)
% Generate original data
  x = -10:0.1:10; % Generate x vals -10 to 10
  y = 2 * x + 1;  % Applying linear function y = 2x + 1

% Add salt
  rng(42); % for reproducibility % Random num gen for results
  salt = rand(size(y)) * 2 - 1;  % Generate random noise -1 - 1
  y_salted = y + salt; % Add noise to y val

% Smooth the salted data
  if nargin < 2
    windowSize = 3; % default windowSize if not provided
  else
    windowSize = input2; % user input for windowSize
  end
    y_smoothed = movmean(y_salted, windowSize); % smooth y vals with avg moving

% Plot 1: Original vs Salted
  figure;
  plot(x, y, 'b-', 'LineWidth', 1.5); hold on; % orginal line plotted in blue
  plot(x, y_salted, 'r.', 'MarkerSize', 10); % salted data plotted as red dots
  title('Original vs Salted Data');
  xlabel('x'); ylabel('y');
  legend('Original Data', 'Salted Data');
  grid on;

% Plot 2: Salted vs Smoothed
  figure;
  plot(x, y_salted, 'r.', 'MarkerSize', 10); hold on; % red dots = salted
  plot(x, y_smoothed, 'g-', 'LineWidth', 1.5); % green line = smoothed
  title('Salted vs Smoothed Data');
  xlabel('x'); ylabel('y');
  legend('Salted Data', 'Smoothed Data');
  grid on;

% Export data to CSV
  output = [x', y_salted', y_smoothed']; % combines all vectors into one
  csvwrite('new_data.csv', output); % saves the CSV
  disp('Data exported to smoothed_data.csv');

% Return output
  retval = output; % return data

endfunction

